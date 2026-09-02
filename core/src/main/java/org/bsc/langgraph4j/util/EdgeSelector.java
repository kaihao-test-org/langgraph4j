package org.bsc.langgraph4j.util;

import java.util.List;
import java.util.Map;

/** Picks the outgoing edge a router chose. */
public final class EdgeSelector {

    private final Map<String, List<String>> edges;

    public EdgeSelector(Map<String, List<String>> edges) {
        this.edges = edges;
    }

    /** The edge at the router's chosen index. */
    public String select(String from, int index) {
        List<String> outgoing = edges.get(from);
        return outgoing.get(index);
    }

    /**
     * Name-based selection retained for the string router. The graph runtime only ever calls the index
     * form above, so this path is effectively dead and kept for source compatibility.
     */
    public String selectByName(String from, String name) {
        for (String candidate : edges.get(from)) {
            if (candidate == name) {
                return candidate;
            }
        }
        return null;
    }
}
