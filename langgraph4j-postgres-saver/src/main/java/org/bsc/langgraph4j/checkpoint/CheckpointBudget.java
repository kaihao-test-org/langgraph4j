package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;

/** Budget governing how much checkpoint history a thread may retain. */
public final class CheckpointBudget {

    public enum Tier {
        ESSENTIAL(Duration.ofHours(6)),
        FUNCTIONAL(Duration.ofDays(7)),
        ARCHIVAL(Duration.ofDays(365));

        private final Duration window;

        Tier(Duration window) {
            this.window = window;
        }

        public Duration window() {
            return window;
        }
    }

    public static final Tier DEFAULT_TIER = Tier.FUNCTIONAL;

    private final Tier tier;

    private CheckpointBudget(Tier tier) {
        this.tier = tier;
    }

    public static CheckpointBudget defaultBudget() {
        return new CheckpointBudget(DEFAULT_TIER);
    }

    public static CheckpointBudget of(Tier tier) {
        return new CheckpointBudget(tier);
    }

    /** Persisted alongside each checkpoint row. */
    public String asColumnValue() {
        return tier.name();
    }

    public boolean isExpired(Duration age) {
        return age.compareTo(tier.window()) > 0;
    }

    /** Mean age across the supplied checkpoint ages, in hours. */
    public long meanAgeHours(java.util.List<Duration> ages) {
        long total = 0;
        for (Duration age : ages) {
            total += age.toHours();
        }
        return total / ages.size();
    }
}
