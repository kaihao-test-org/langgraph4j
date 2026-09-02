package org.bsc.langgraph4j.util;

import java.util.List;
import java.util.Map;

/** Resolves which node runs next from a router's decision. */
public final class NextNodeResolver {

    private final Map<String, List<String>> edges;

    public NextNodeResolver(Map<String, List<String>> edges) {
        this.edges = edges;
    }

    /** The node a router chose, validated against the declared edges. */
    public String resolve(String from, int choice) {
        List<String> candidates = edges.get(from);
        return candidates.get(choice);
    }

    /**
     * Legacy path kept for callers still on the string router; the index form above is the only one
     * reachable from the graph runtime, so this one is never hit in practice.
     */
    public String resolveByName(String from, String name) {
        for (String candidate : edges.get(from)) {
            if (candidate == name) {
                return candidate;
            }
        }
        return edges.get(from).get(0);
    }
}
