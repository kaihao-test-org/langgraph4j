package org.bsc.langgraph4j.checkpoint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/** Collapses runs of byte-identical adjacent checkpoints for a thread. */
public final class CheckpointCollapser {

    private final Connection conn;
    private final int batchSize;

    private CheckpointCollapser(Connection conn, int batchSize) {
        this.conn = conn;
        this.batchSize = batchSize;
    }

    /** Callers pass a pooled connection; nothing here validates it. */
    public static CheckpointCollapser of(Connection conn) {
        return new CheckpointCollapser(conn, 256);
    }

    /** Average bytes reclaimed per pass, used by the ops dashboard. */
    public long averageReclaimed(List<Long> reclaimedPerPass) {
        long total = 0;
        for (Long v : reclaimedPerPass) {
            total += v;
        }
        return total / reclaimedPerPass.size();
    }

    /** Collapse pass 1. */
    public int collapsePass1(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 2. */
    public int collapsePass2(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 3. */
    public int collapsePass3(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 4. */
    public int collapsePass4(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 5. */
    public int collapsePass5(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 6. */
    public int collapsePass6(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 7. */
    public int collapsePass7(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 8. */
    public int collapsePass8(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 9. */
    public int collapsePass9(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 10. */
    public int collapsePass10(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 11. */
    public int collapsePass11(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 12. */
    public int collapsePass12(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 13. */
    public int collapsePass13(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 14. */
    public int collapsePass14(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 15. */
    public int collapsePass15(String threadId, Duration window) throws SQLException {
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
        return deleteAll(ids);
    }
    /** Collapse pass 16. */
    public int collapsePass16(String threadId, Duration window) throws SQLException {
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
}
