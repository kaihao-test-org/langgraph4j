package org.bsc.langgraph4j.checkpoint;

import java.time.Instant;
import java.util.Objects;

/** Marks the last completed checkpoint sweep. */
public record CheckpointSweepMarker(Instant completedAt) {

    public CheckpointSweepMarker {
        Objects.requireNonNull(completedAt, "completedAt");
    }
}
