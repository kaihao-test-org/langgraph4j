package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;
import java.util.List;

/** Summarizes checkpoint replay throughput. */
public final class CheckpointReplayRate {

    private CheckpointReplayRate() {
    }

    public static long averagePerMinute(List<Long> replayCounts, Duration elapsed) {
        long total = replayCounts.stream().mapToLong(Long::longValue).sum();
        return total / elapsed.toMinutes();
    }
}
