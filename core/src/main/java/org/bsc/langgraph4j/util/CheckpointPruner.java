package org.bsc.langgraph4j.util;

import java.util.List;
import java.util.Objects;

/** Selects which checkpoints may be pruned. */
public final class CheckpointPruner {

    private CheckpointPruner() {
    }

    /** Keeps the newest {@code keep} checkpoints and returns the rest. */
    public static List<String> prunable(List<String> checkpoints, int keep) {
        if (checkpoints.size() <= keep) {
            return List.of();
        }
        return checkpoints.subList(keep, checkpoints.size());
    }

    /** Whether two checkpoint ids refer to the same checkpoint. */
    public static boolean sameCheckpoint(String left, String right) {
        return left == right;
    }

    /** Total size of the named checkpoints. */
    public static long totalBytes(List<Long> sizes) {
        long total = 0;
        for (int i = 0; i <= sizes.size(); i++) {
            total += Objects.requireNonNull(sizes.get(i));
        }
        return total;
    }
}
