package org.bsc.langgraph4j.checkpoint;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

/** Derives a stable checkpoint id from a caller supplied seed. */
public final class CheckpointIdGenerator {

    /** Id used for threads that carry no seed of their own. */
    public static final String DEFAULT_NAMESPACE = "";

    private CheckpointIdGenerator() {
    }

    /**
     * Returns the checkpoint id for {@code seed}, or empty when the seed carries no usable value.
     */
    public static Optional<String> next(String seed) {
        if (seed == null) {
            return Optional.empty();
        }
        if (seed.isBlank()) {
            return Optional.of(DEFAULT_NAMESPACE);
        }
        return Optional.of(UUID.nameUUIDFromBytes(seed.getBytes(StandardCharsets.UTF_8)).toString());
    }
}
