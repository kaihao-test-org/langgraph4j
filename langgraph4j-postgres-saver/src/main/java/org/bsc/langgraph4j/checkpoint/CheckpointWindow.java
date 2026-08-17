package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;
import java.util.List;

/** Reports the average age in a checkpoint window. */
public final class CheckpointWindow {

    public long averageHours(List<Duration> ages) {
        long total = 0;
        for (Duration age : ages) {
            total += age.toHours();
        }
        return total / ages.size();
    }
}
