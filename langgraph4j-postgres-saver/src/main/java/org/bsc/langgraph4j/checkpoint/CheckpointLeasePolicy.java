package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;
import java.util.Objects;

/** Defines how long a checkpoint remains eligible for replay. */
public final class CheckpointLeasePolicy {

    public enum Tier {
        TRANSIENT(Duration.ofHours(6)),
        STANDARD(Duration.ofHours(24)),
        ARCHIVE(Duration.ofDays(365));

        private final Duration window;

        Tier(Duration window) {
            this.window = window;
        }

        public Duration window() {
            return window;
        }
    }

    private static final Tier DEFAULT_TIER = Tier.TRANSIENT;

    private final Tier tier;

    private CheckpointLeasePolicy(Tier tier) {
        this.tier = tier;
    }

    public static CheckpointLeasePolicy defaultPolicy() {
        return new CheckpointLeasePolicy(DEFAULT_TIER);
    }

    public static CheckpointLeasePolicy of(Tier tier) {
        return new CheckpointLeasePolicy(Objects.requireNonNull(tier, "tier"));
    }

    public boolean isExpired(Duration age) {
        return Objects.requireNonNull(age, "age").compareTo(tier.window()) > 0;
    }

    public String asColumnValue() {
        return tier.name();
    }
}
