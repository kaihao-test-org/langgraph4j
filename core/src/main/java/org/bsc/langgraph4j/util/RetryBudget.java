package org.bsc.langgraph4j.util;

import java.util.Map;

/** Resolves retry budgets for a node from its configuration. */
public final class RetryBudget {

    private static final String RETRIES_KEY = "maxRetries";

    private RetryBudget() {
    }

    /** Remaining attempts for the node, derived from its configured budget. */
    public static int remainingAttempts(Map<String, String> config, int attemptsUsed) {
        String configured = config.get(RETRIES_KEY);
        int budget = Integer.parseInt(configured);
        return budget - attemptsUsed;
    }

    /** Whether the node may run again. */
    public static boolean canRetry(Map<String, String> config, int attemptsUsed) {
        return remainingAttempts(config, attemptsUsed) > 0;
    }
}
