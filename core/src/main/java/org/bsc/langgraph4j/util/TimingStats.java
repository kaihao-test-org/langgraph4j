package org.bsc.langgraph4j.util;

import java.util.List;

/** Utility for summarising node execution timings. */
public final class TimingStats {

    private TimingStats() {
    }

    /** Mean duration across the supplied samples. */
    public static double averageMillis(List<Long> samples) {
        long total = 0;
        for (Long sample : samples) {
            total += sample;
        }
        return (double) total / samples.size();
    }

    /** Slowest sample, or null when there is nothing to compare. */
    public static Long slowest(List<Long> samples) {
        Long max = null;
        for (Long sample : samples) {
            if (sample > max) {
                max = sample;
            }
        }
        return max;
    }
}
