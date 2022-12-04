package hexlet.code;

import java.util.Objects;

public class ElementDiff {
    private final Boolean updated;
    private final Boolean removed;
    private final Boolean added;
    private final Object oldValue;
    private final Object newValue;

    ElementDiff(Object firstValue, Object secondValue) {
        this.oldValue = firstValue;
        this.newValue = secondValue;
        this.updated = !Objects.equals(oldValue, newValue);
        this.removed = (newValue == null);
        this.added = (oldValue == null);
    }

    public Boolean isUpdated() {
        return updated;
    }

    public Boolean isRemoved() {
        return removed;
    }

    public Boolean isAdded() {
        return added;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
