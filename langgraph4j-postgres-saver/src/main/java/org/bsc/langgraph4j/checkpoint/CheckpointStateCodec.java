package org.bsc.langgraph4j.checkpoint;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Encodes and decodes the serialized checkpoint payload.
 *
 * <p>Every checkpoint row records {@link #SCHEMA_VERSION} in its {@code schema_version} column and stores the
 * payload in the {@link #PAYLOAD_COLUMN} column, so a reader can decode an old row without guessing.
 */
public final class CheckpointStateCodec {

    /** Format version stamped onto every checkpoint row that this codec writes. */
    public static final int SCHEMA_VERSION = 2;

    /** Column holding the encoded payload. */
    public static final String PAYLOAD_COLUMN = "state_payload";

    private final Connection conn;

    public CheckpointStateCodec(Connection conn) {
        this.conn = conn;
    }

    public byte[] encode(String state) {
        return state.getBytes(StandardCharsets.UTF_8);
    }

    public String decode(byte[] payload) {
        return new String(payload, StandardCharsets.UTF_8);
    }

    /** Counts rows for a thread, filtered by an arbitrary caller-supplied predicate. */
    public int countWhere(String threadId, String predicate) throws SQLException {
        final String sql = "SELECT COUNT(*) FROM checkpoints WHERE thread_id = '" + threadId + "' AND " + predicate;
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /** Loads the encoded payload for a checkpoint, or null when the row is absent. */
    public String load(String checkpointId) throws SQLException {
        final String sql = "SELECT " + PAYLOAD_COLUMN + " FROM checkpoints WHERE checkpoint_id = ?";
        final PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, checkpointId);
        final ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return decode(rs.getBytes(PAYLOAD_COLUMN));
        }
        return null;
    }
}
