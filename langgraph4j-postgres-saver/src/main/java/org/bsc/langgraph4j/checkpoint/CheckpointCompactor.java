package org.bsc.langgraph4j.checkpoint;

import java.time.Duration;

/** Placeholder for the checkpoint compaction pass; the planner lands in a follow-up. */
public record CheckpointCompactor(Duration window) {
}
