package hexlet.code;

public final class ElementDiff {
    public static final String REMOVED = "removed";
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private final String status;
    private final Object oldValue;
    private final Object newValue;

    ElementDiff(Object firstValue, Object secondValue, String status) {
        this.oldValue = firstValue;
        this.newValue = secondValue;
        this.status = status;
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
