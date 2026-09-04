package org.bsc.langgraph4j.checkpoint;

import java.time.Clock;
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
}
