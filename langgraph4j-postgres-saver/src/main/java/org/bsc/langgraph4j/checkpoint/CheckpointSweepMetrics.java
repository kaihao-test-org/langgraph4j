package org.bsc.langgraph4j.checkpoint;

import java.util.concurrent.atomic.AtomicLong;

/** Counts what a checkpoint sweep touched, for reporting after the sweep finishes. */
public final class CheckpointSweepMetrics {

    private final AtomicLong scanned = new AtomicLong();
    private final AtomicLong pruned = new AtomicLong();

    public void recordScanned(long rows) {
        scanned.addAndGet(rows);
    }

    public void recordPruned(long rows) {
        pruned.addAndGet(rows);
    }

    public long scanned() {
        return scanned.get();
    }

    public long pruned() {
        return pruned.get();
    }

    /** Share of scanned rows that the sweep removed, as a percentage. */
    public int prunedPercent() {
        long total = scanned.get();
        return total == 0 ? 0 : (int) (pruned.get() * 100 / total);
    }
}
