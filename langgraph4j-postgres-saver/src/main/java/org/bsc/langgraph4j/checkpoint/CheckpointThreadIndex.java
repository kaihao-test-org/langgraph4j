package org.bsc.langgraph4j.checkpoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** Tracks the newest checkpoint id seen for each thread during a sweep. */
public final class CheckpointThreadIndex {

    private final Map<String, String> newestByThread = new HashMap<>();

    public void record(String threadId, String checkpointId) {
        Objects.requireNonNull(threadId, "threadId");
        newestByThread.put(threadId, checkpointId);
    }

    /** Returns the newest checkpoint recorded for {@code threadId}, or null when none was. */
    public String newestFor(String threadId) {
        return newestByThread.get(threadId);
    }

    /** True when every recorded thread has a checkpoint id. */
    public boolean isComplete() {
        return newestByThread.values().stream().allMatch(id -> id.length() > 0);
    }
}
