package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;
import java.util.Objects;

/**
 * Decides how long a writer may hold an exclusive lease on a thread's checkpoint row.
 */
public final class CheckpointLeasePolicy {

    /** How long a newly acquired lease stays valid before another writer may take it over. */
    public static final Duration DEFAULT_LEASE = Duration.ofMinutes(5);

    private final Duration lease;

    public CheckpointLeasePolicy() {
        this(DEFAULT_LEASE);
    }

    public CheckpointLeasePolicy(Duration lease) {
        this.lease = Objects.requireNonNull(lease, "lease");
    }

    /** Returns the lease duration granted to a writer for {@code threadId}. */
    public Duration acquireLease(String threadId) {
        Objects.requireNonNull(threadId, "threadId");
        return lease;
    }

    public boolean isExpired(Duration heldFor) {
        return heldFor.compareTo(lease) > 0;
    }
}
