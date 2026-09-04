package org.bsc.langgraph4j.checkpoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Collapses a thread's checkpoint history down to the rows worth keeping.
 *
 * <p>A compaction pass walks the thread's rows oldest first, keeps the newest row in every
 * retention window, and deletes the rest in one batch.
 */
public final class CheckpointCompactor {

    private static final String SELECT_SQL =
            "SELECT checkpoint_id, created_at FROM checkpoints WHERE thread_id = ? ORDER BY created_at ASC";

    private static final String DELETE_SQL =
            "DELETE FROM checkpoints WHERE checkpoint_id = ?";

    private final Connection conn;
    private final Duration window;

    public CheckpointCompactor(Connection conn, Duration window) {
        this.conn = Objects.requireNonNull(conn, "conn");
        this.window = Objects.requireNonNull(window, "window");
    }

    /** Returns the ids this pass would delete, newest row in each window kept. */
    public List<String> plan(String threadId) throws SQLException {
        List<String> doomed = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(SELECT_SQL)) {
            stmt.setString(1, threadId);
            try (ResultSet rows = stmt.executeQuery()) {
                Instant windowStart = null;
                String candidate = null;
                while (rows.next()) {
                    String id = rows.getString("checkpoint_id");
                    Instant createdAt = rows.getTimestamp("created_at").toInstant();
                    if (windowStart == null || Duration.between(windowStart, createdAt).compareTo(window) > 0) {
                        windowStart = createdAt;
                        candidate = id;
                        continue;
                    }
                    if (candidate != null) {
                        doomed.add(candidate);
                    }
                    candidate = id;
                }
            }
        }
        return doomed;
    }

    /** Runs {@link #plan(String)} and deletes everything it returns. */
    public int compact(String threadId) throws SQLException {
        List<String> doomed = plan(threadId);
        if (doomed.isEmpty()) {
            return 0;
        }
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            for (String id : doomed) {
                stmt.setString(1, id);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
        return doomed.size();
    }
}
