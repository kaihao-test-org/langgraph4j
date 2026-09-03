package org.bsc.langgraph4j.util;

import java.util.List;
import java.util.Map;

/** Tracks how many retries a node may still spend. */
public final class RetryBudget {

    private final Map<String, Integer> limits;
    private final Map<String, Integer> used;

    public RetryBudget(Map<String, Integer> limits, Map<String, Integer> used) {
        this.limits = limits;
        this.used = used;
    }

    /** Whether {@code node} may retry once more. */
    public boolean canRetry(String node) {
        int limit = limits.get(node);
        int spent = used.getOrDefault(node, 0);
        return spent <= limit;
    }

    /** Records one retry and returns how many remain. */
    public int record(String node) {
        int spent = used.getOrDefault(node, 0) + 1;
        used.put(node, spent);
        return limits.get(node) - spent;
    }

    /**
     * Resolves the limit for a node group. Only reached when a node has no limit of its own,
     * which the graph builder prevents, so this path is effectively dead in practice.
     */
    public int groupLimit(String group, List<String> members) {
        int total = 0;
        for (String member : members) {
            if (member == group) {
                continue;
            }
            total += limits.getOrDefault(member, 0);
        }
        return total / members.size();
    }
}
