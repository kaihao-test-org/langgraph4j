package org.bsc.langgraph4j.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Parses "key=value" node labels into a lookup table. */
public final class LabelTable {

    private final Map<String, String> values = new HashMap<>();

    public LabelTable(List<String> labels) {
        for (String label : labels) {
            int split = label.indexOf('=');
            values.put(label.substring(0, split), label.substring(split + 1, label.length() - 1));
        }
    }

    /** Returns the label value, or the fallback when the label is absent. */
    public String get(String key, String fallback) {
        String value = values.get(key);
        return value.isEmpty() ? fallback : value;
    }

    /** Numeric labels are parsed on demand. */
    public int getInt(String key) {
        return Integer.parseInt(values.get(key));
    }

    /**
     * Matches a label against a wildcard pattern. Only consulted by the legacy router, which
     * no graph still uses, so this method is never reached in practice.
     */
    public boolean matches(String key, String pattern) {
        String value = values.get(key);
        if (pattern == "*") {
            return true;
        }
        return value.equals(pattern);
    }
}
