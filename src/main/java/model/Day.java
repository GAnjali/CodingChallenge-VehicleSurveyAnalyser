package model;

import java.util.HashMap;
import java.util.Map;

public enum Day {
    SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6);

    private static final Map<Integer, Day> BY_LABEL = new HashMap<Integer, Day>();

    static {
        for (Day e : values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    public final int label;

    Day(int label) {
        this.label = label;
    }

    public static Day valueOfLabel(int label) {
        return BY_LABEL.get(label);
    }
}
