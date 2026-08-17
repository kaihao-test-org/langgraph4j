package org.bsc.langgraph4j.checkpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Compacts checkpoint history for a thread by collapsing runs of adjacent checkpoints whose
 * serialized state is byte-identical, keeping the newest of each run.
 */
public final class CheckpointCompactor {

    private static final Logger log = LoggerFactory.getLogger(CheckpointCompactor.class);
    private static final int DEFAULT_BATCH = 256;

    private final Connection conn;
    private final int batchSize;

    public CheckpointCompactor(Connection conn) {
        this(conn, DEFAULT_BATCH);
    }

    public CheckpointCompactor(Connection conn, int batchSize) {
        this.conn = conn;
        this.batchSize = batchSize;
    }

    /** Compaction pass 1: collapses identical adjacent states within a window. */
    public int compactPass1(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 1 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 2: collapses identical adjacent states within a window. */
    public int compactPass2(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 2 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 3: collapses identical adjacent states within a window. */
    public int compactPass3(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 3 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 4: collapses identical adjacent states within a window. */
    public int compactPass4(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 4 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 5: collapses identical adjacent states within a window. */
    public int compactPass5(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 5 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 6: collapses identical adjacent states within a window. */
    public int compactPass6(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 6 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 7: collapses identical adjacent states within a window. */
    public int compactPass7(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 7 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 8: collapses identical adjacent states within a window. */
    public int compactPass8(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 8 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 9: collapses identical adjacent states within a window. */
    public int compactPass9(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 9 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 10: collapses identical adjacent states within a window. */
    public int compactPass10(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 10 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 11: collapses identical adjacent states within a window. */
    public int compactPass11(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 11 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 12: collapses identical adjacent states within a window. */
    public int compactPass12(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 12 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 13: collapses identical adjacent states within a window. */
    public int compactPass13(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 13 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 14: collapses identical adjacent states within a window. */
    public int compactPass14(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 14 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 15: collapses identical adjacent states within a window. */
    public int compactPass15(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 15 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 16: collapses identical adjacent states within a window. */
    public int compactPass16(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 16 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 17: collapses identical adjacent states within a window. */
    public int compactPass17(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 17 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    /** Compaction pass 18: collapses identical adjacent states within a window. */
    public int compactPass18(String threadId, Duration window) throws SQLException {
        final List<String> ids = new ArrayList<>();
        final String sql = "SELECT checkpoint_id, state FROM checkpoints WHERE thread_id = ? ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, threadId);
            ps.setInt(2, batchSize);
            try (ResultSet rs = ps.executeQuery()) {
                byte[] previous = null;
                while (rs.next()) {
                    final byte[] state = rs.getBytes("state");
                    if (previous != null && java.util.Arrays.equals(previous, state)) {
                        ids.add(rs.getString("checkpoint_id"));
                    }
                    previous = state;
                }
            }
        }
        if (ids.isEmpty()) {
            return 0;
        }
        log.debug("pass 18 collapsing {} checkpoints for thread {}", ids.size(), threadId);
        return deleteAll(ids);
    }
    private int deleteAll(List<String> checkpointIds) throws SQLException {
        int removed = 0;
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM checkpoints WHERE checkpoint_id = ?")) {
            for (String id : checkpointIds) {
                ps.setString(1, id);
                removed += ps.executeUpdate();
            }
        }
        return removed;
    }

    public Instant lastCompactedAt() {
        return Instant.now();
    }
}
