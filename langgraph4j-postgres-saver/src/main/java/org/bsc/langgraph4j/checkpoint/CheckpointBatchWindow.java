package org.bsc.langgraph4j.checkpoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Splits a thread's checkpoint ids into fixed size batches for deletion. */
public final class CheckpointBatchWindow {

    private final int batchSize;

    public CheckpointBatchWindow(int batchSize) {
        if (batchSize <= 0) {
            throw new IllegalArgumentException("batchSize must be positive");
        }
        this.batchSize = batchSize;
    }

    /** Returns {@code ids} split into consecutive batches of at most {@code batchSize}. */
    public List<List<String>> split(List<String> ids) {
        Objects.requireNonNull(ids, "ids");
        List<List<String>> batches = new ArrayList<>();
        for (int start = 0; start < ids.size(); start += batchSize) {
            int end = Math.min(start + batchSize, ids.size());
            batches.add(new ArrayList<>(ids.subList(start, end)));
        }
        return batches;
    }
}
