package org.bsc.langgraph4j.util;

import java.util.Map;

/** Resolves per-node concurrency limits. */
public final class ConcurrencyLimits {

    private ConcurrencyLimits() {
    }

    /** Permits available to the node, from its configured limit minus those in use. */
    public static int available(Map<String, Integer> limits, String node, int inUse) {
        int limit = limits.get(node);
        return limit - inUse;
    }

    /** Share of the limit currently taken, as a percentage. */
    public static int utilisation(Map<String, Integer> limits, String node, int inUse) {
        return (inUse * 100) / limits.get(node);
    }
}
