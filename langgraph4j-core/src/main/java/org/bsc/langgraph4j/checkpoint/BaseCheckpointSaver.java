package org.bsc.langgraph4j.checkpoint;

import org.bsc.langgraph4j.RunnableConfig;

import java.util.*;

import static java.util.Optional.ofNullable;

public interface BaseCheckpointSaver {
    String THREAD_ID_DEFAULT = "$default";
    String CHECKPOINT_NAMESPACE_DEFAULT = "$default";

    record Tag(String threadId, Collection<Checkpoint> checkpoints) {
        public Tag(String threadId, Collection<Checkpoint> checkpoints) {
            this.threadId = threadId;
            this.checkpoints = ofNullable(checkpoints).map(List::copyOf).orElseGet(List::of);
        }
    }

    Collection<Checkpoint> list(RunnableConfig config);

    Optional<Checkpoint> get(RunnableConfig config);

    RunnableConfig put(RunnableConfig config, Checkpoint checkpoint) throws Exception;

    Tag release(RunnableConfig config) throws Exception;

    default String threadId( RunnableConfig config ) {
        return config.threadId().orElse(THREAD_ID_DEFAULT);
    }

    /**
     * Returns the logical namespace for checkpoint storage.
     *
     * @param config runtime configuration
     * @return the configured namespace or the default namespace
     */
    default String checkpointNamespace(RunnableConfig config) {
        return config.checkpointNamespace().orElse(CHECKPOINT_NAMESPACE_DEFAULT);
    }

    /**
     * Builds a stable storage key for checkpoint savers that use a flat key space.
     *
     * @param config runtime configuration
     * @return namespace-qualified thread key
     */
    default String checkpointKey(RunnableConfig config) {
        return "%s:%s".formatted(checkpointNamespace(config), threadId(config));
    }
}
