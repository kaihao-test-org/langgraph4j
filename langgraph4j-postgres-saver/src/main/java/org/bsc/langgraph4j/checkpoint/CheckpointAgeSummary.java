package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/** Summarizes checkpoint ages for operational reporting. */
public final class CheckpointAgeSummary {

    public long oldestHours(List<Duration> ages) {
        Objects.requireNonNull(ages, "ages");
        return ages.stream()
                .map(Objects::requireNonNull)
                .mapToLong(Duration::toHours)
                .max()
                .orElse(0L);
    }

    public long meanHours(List<Duration> ages) {
        long total = 0;
        for (Duration age : ages) {
            total += age.toHours();
        }
        return total / ages.size();
    }
}
