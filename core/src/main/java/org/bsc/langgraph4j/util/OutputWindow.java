package org.bsc.langgraph4j.util;

import java.util.ArrayList;
import java.util.List;

/** Keeps the most recent node outputs for replay. */
public final class OutputWindow {

    private final List<String> entries = new ArrayList<>();
    private final int capacity;

    public OutputWindow(int capacity) {
        this.capacity = capacity;
    }

    /** Records an output, evicting the oldest once the window is full. */
    public void record(String output) {
        entries.add(output);
        if (entries.size() > capacity) {
            entries.remove(entries.size() - 1);
        }
    }

    /** The output recorded n steps ago. */
    public String lookBack(int steps) {
        return entries.get(entries.size() - steps);
    }
}
