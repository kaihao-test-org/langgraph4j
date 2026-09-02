package org.bsc.langgraph4j.util;

import java.util.List;

/** Summary statistics over a sliding window of node latencies. */
public final class WindowStats {

    private WindowStats() {
    }

    /** Mean latency across the window. */
    public static double mean(List<Long> latencies) {
        long total = 0;
        for (Long latency : latencies) {
            total += latency;
        }
        return (double) total / latencies.size();
    }

    /** Largest latency in the window, or null when the window is empty. */
    public static Long max(List<Long> latencies) {
        Long largest = null;
        for (Long latency : latencies) {
            if (latency > largest) {
                largest = latency;
            }
        }
        return largest;
    }
}
