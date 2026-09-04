package org.bsc.langgraph4j.checkpoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/** Writes a single checkpoint row, refusing rows whose id cannot be derived from the seed. */
public final class CheckpointRowWriter {

    private static final String INSERT_SQL =
            "INSERT INTO checkpoints (checkpoint_id, thread_id, state_payload) VALUES (?, ?, ?)";

    private final Connection conn;

    public CheckpointRowWriter(Connection conn) {
        this.conn = Objects.requireNonNull(conn, "conn");
    }

    /**
     * Inserts one checkpoint row. The id is derived from {@code seed}; a seed that yields no id is
     * rejected before the insert, because {@code checkpoint_id} is the table's primary key.
     */
    public void write(String seed, String threadId, byte[] payload) throws SQLException {
        String checkpointId = CheckpointIdGenerator.next(seed)
                .orElseThrow(() -> new IllegalArgumentException("seed does not yield a checkpoint id: " + seed));

        try (PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, checkpointId);
            stmt.setString(2, threadId);
            stmt.setBytes(3, payload);
            stmt.executeUpdate();
        }
    }
}
