package org.bsc.langgraph4j.checkpoint;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/** Supplies the timestamps stamped onto checkpoint rows. */
public final class CheckpointClock {

    private final Clock clock;

    public CheckpointClock(Clock clock) {
        this.clock = Objects.requireNonNull(clock, "clock");
    }

    public Instant now() {
        return clock.instant();
    }

    /** Whether {@code stamped} is older than {@code maxAge} relative to this clock. */
    public boolean isStale(Instant stamped, Duration maxAge) {
        return Duration.between(stamped, now()).compareTo(maxAge) > 0;
    }
}
