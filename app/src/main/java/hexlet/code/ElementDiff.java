package hexlet.code;

public final class ElementDiff {
    public static final String REMOVED = "removed";
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private final String status;
    private final Object value;

    ElementDiff(Object elemValue, String elemStatus) {
        this.value = elemValue;
        this.status = elemStatus;
    }

    public String getStatus() {
        return this.status;
    }

    public Object getValue() {
        return value;
    }
}
