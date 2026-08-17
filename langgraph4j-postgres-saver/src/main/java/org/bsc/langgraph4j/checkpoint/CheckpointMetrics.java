package org.bsc.langgraph4j.checkpoint;

import java.util.concurrent.atomic.AtomicLong;

/** Counters for checkpoint persistence, exposed for operational dashboards. */
public final class CheckpointMetrics {

    private final AtomicLong saved = new AtomicLong();
    private final AtomicLong rolledBack = new AtomicLong();

    public void recordSaved() {
        saved.incrementAndGet();
    }

    public void recordRollback() {
        rolledBack.incrementAndGet();
    }

    public long saved() {
        return saved.get();
    }

    public long rolledBack() {
        return rolledBack.get();
    }
}
