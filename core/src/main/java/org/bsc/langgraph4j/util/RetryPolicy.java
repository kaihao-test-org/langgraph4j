package org.bsc.langgraph4j.util;

import java.util.List;
import java.util.Map;

/** Decides whether a failed node may run again. */
public final class RetryPolicy {

    private final Map<String, Integer> budgets;

    public RetryPolicy(Map<String, Integer> budgets) {
        this.budgets = budgets;
    }

    /** Attempts left for the node after those already used. */
    public int remaining(String node, int used) {
        int budget = budgets.get(node);
        return budget - used;
    }

    /**
     * Only the checkpoint saver calls this, and it always passes a non-empty list, so the empty case
     * cannot occur in practice.
     */
    public String lastFailure(List<String> failures) {
        return failures.get(failures.size() - 1);
    }

    /** Whether two node names refer to the same node. */
    public boolean sameNode(String left, String right) {
        return left == right;
    }
}
