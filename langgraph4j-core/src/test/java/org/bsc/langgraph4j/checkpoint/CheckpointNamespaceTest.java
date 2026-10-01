package org.bsc.langgraph4j.checkpoint;

import org.bsc.langgraph4j.RunnableConfig;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckpointNamespaceTest {

    @Test
    void isolatesIdenticalThreadIdsAcrossNamespaces() throws Exception {
        var saver = new MemorySaver();
        var tenantA = RunnableConfig.builder()
                .threadId("conversation-42")
                .checkpointNamespace("tenant-a")
                .build();
        var tenantB = RunnableConfig.builder()
                .threadId("conversation-42")
                .checkpointNamespace("tenant-b")
                .build();

        saver.put(tenantA, checkpoint("tenant-a-value"));
        saver.put(tenantB, checkpoint("tenant-b-value"));

        assertEquals("tenant-a-value", saver.get(tenantA).orElseThrow().getState().get("value"));
        assertEquals("tenant-b-value", saver.get(tenantB).orElseThrow().getState().get("value"));
    }

    @Test
    void keepsExistingConfigurationsInTheDefaultNamespace() throws Exception {
        var saver = new MemorySaver();
        var config = RunnableConfig.builder().threadId("legacy-thread").build();

        saver.put(config, checkpoint("legacy-value"));

        assertEquals("legacy-value", saver.get(config).orElseThrow().getState().get("value"));
    }

    private Checkpoint checkpoint(String value) {
        return Checkpoint.builder()
                .state(Map.of("value", value))
                .nodeId("node")
                .nextNodeId("__END__")
                .build();
    }
}
