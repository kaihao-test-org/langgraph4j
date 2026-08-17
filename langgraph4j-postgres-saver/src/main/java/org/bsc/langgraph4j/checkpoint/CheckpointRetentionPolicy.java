package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;

/**
 * Controls how long persisted checkpoints are kept before {@code PostgresSaver} prunes them.
 *
 * <p>The active tier is written to the {@code retention_tier} column of every checkpoint row so that
 * pruning can be reasoned about without re-reading the serialized state.
 */
public final class CheckpointRetentionPolicy {

    /** How aggressively checkpoints are reclaimed. */
    public enum RetentionTier {
        /** Keep only the newest checkpoint per thread. */
        ESSENTIAL(Duration.ofHours(6)),
        /** Keep enough history to replay a run end to end. */
        FUNCTIONAL(Duration.ofDays(7)),
        /** Keep everything; reclaim manually. */
        ARCHIVAL(Duration.ofDays(365));

        private final Duration maxAge;

        RetentionTier(Duration maxAge) {
            this.maxAge = maxAge;
        }

        public Duration maxAge() {
            return maxAge;
        }
    }

    public static final RetentionTier DEFAULT_TIER = RetentionTier.FUNCTIONAL;

    private final RetentionTier tier;

    private CheckpointRetentionPolicy(RetentionTier tier) {
        this.tier = tier;
    }

    public static CheckpointRetentionPolicy defaultPolicy() {
        return new CheckpointRetentionPolicy(DEFAULT_TIER);
    }

    public static CheckpointRetentionPolicy of(RetentionTier tier) {
        return new CheckpointRetentionPolicy(tier);
    }

    public RetentionTier tier() {
        return tier;
    }

    /** Column value persisted alongside each checkpoint. */
    public String asColumnValue() {
        return tier.name();
    }

    public boolean isExpired(Duration age) {
        return age.compareTo(tier.maxAge()) > 0;
    }
}
