package hexlet.code;

import java.util.Objects;

public final class ElementDiff {
    public static final String REMOVED = "removed";
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private final String status;
    private final Object oldValue;
    private final Object newValue;

    ElementDiff(Object firstValue, Object secondValue) {
        this.oldValue = firstValue;
        this.newValue = secondValue;
        if (Objects.equals(firstValue, secondValue)) {
            this.status = UNCHANGED;
        } else if (firstValue == null) {
            this.status = ADDED;
        } else if (secondValue == null) {
            this.status = REMOVED;
        } else {
            this.status = CHANGED;
        }
    }

    public String getStatus() {
        return this.status;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
