package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;
import java.util.Objects;

/** Human readable summary of one checkpoint sweep, rendered for the operator log. */
public record CheckpointSweepReport(long scanned, long pruned, Duration took) {

    public CheckpointSweepReport {
        Objects.requireNonNull(took, "took");
    }

    public String render() {
        return "swept %d rows, pruned %d, in %d ms".formatted(scanned, pruned, took.toMillis());
    }
}
