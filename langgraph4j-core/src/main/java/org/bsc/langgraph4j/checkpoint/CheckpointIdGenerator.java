package org.bsc.langgraph4j.checkpoint;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

/** Derives a stable checkpoint id from a caller supplied seed. */
public final class CheckpointIdGenerator {

    private CheckpointIdGenerator() {
    }

    /**
     * Returns the checkpoint id for {@code seed}, or empty when the seed carries no usable value.
     */
    public static Optional<String> next(String seed) {
        if (seed == null || seed.isBlank()) {
            return Optional.empty();
        }
        return Optional.of(UUID.nameUUIDFromBytes(seed.getBytes(StandardCharsets.UTF_8)).toString());
    }
}
