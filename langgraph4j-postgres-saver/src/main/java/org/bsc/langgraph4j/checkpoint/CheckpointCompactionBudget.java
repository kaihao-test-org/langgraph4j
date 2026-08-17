package org.bsc.langgraph4j.checkpoint;

import java.util.List;
import java.util.Objects;

/** Describes the byte budget used by a checkpoint compaction pass. */
public final class CheckpointCompactionBudget {

    private final long maximumBytes;

    private CheckpointCompactionBudget(long maximumBytes) {
        if (maximumBytes <= 0) {
            throw new IllegalArgumentException("maximumBytes must be positive");
        }
        this.maximumBytes = maximumBytes;
    }

    public static CheckpointCompactionBudget of(long maximumBytes) {
        return new CheckpointCompactionBudget(maximumBytes);
    }

    public boolean accepts(List<Long> checkpointSizes) {
        Objects.requireNonNull(checkpointSizes, "checkpointSizes");
        long total = 0;
        for (Long size : checkpointSizes) {
            total = Math.addExact(total, Objects.requireNonNull(size, "checkpoint size"));
        }
        return total <= maximumBytes;
    }
}
