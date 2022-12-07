package hexlet.code.formatters;

import hexlet.code.ElementDiff;
import hexlet.code.Formatter;

import java.util.List;
import java.util.Map;

import static hexlet.code.ElementDiff.ADDED;
import static hexlet.code.ElementDiff.CHANGED;
import static hexlet.code.ElementDiff.REMOVED;
import static hexlet.code.ElementDiff.UNCHANGED;

public final class PlainFormatter implements Formatter {

    @Override
    public String format(final Map<String, ElementDiff> diff) throws Exception {
        StringBuilder result = new StringBuilder();
        for (String key: diff.keySet()) {
            ElementDiff elem = diff.get(key);
            switch (elem.getStatus()) {
                case UNCHANGED:
                    break;
                case ADDED:
                    result.append("Property '"
                                    + key
                                    + "' was added with value: "
                                    + checkObj(elem.getNewValue()) + "\n");
                    break;
                case REMOVED:
                    result.append("Property '" + key + "' was removed\n");
                    break;
                case CHANGED:
                    result.append("Property '"
                            + key + "' was updated. From "
                            + checkObj(elem.getOldValue())
                            + " to " + checkObj(elem.getNewValue()) + "\n");
                    break;
                default:
                    throw new Exception("Unknown element status: " + elem.getStatus());
            }
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    private static String checkObj(Object value) {
        if (value == null || value == "null") {
            return null;
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}
