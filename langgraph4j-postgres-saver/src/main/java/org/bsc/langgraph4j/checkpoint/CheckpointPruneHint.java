package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;

/** Advises how long a thread's checkpoints should be retained before pruning. */
public record CheckpointPruneHint(String threadId, Duration keepFor) {

    public static CheckpointPruneHint keepAll(String threadId) {
        return new CheckpointPruneHint(threadId, Duration.ofDays(365));
    }

    public boolean isExpired(Duration age) {
        return age.compareTo(keepFor) > 0;
    }
}
