package hexlet.code.formatters;

import hexlet.code.ElementDiff;
import hexlet.code.Formatter;

import java.util.Map;

import static hexlet.code.ElementDiff.ADDED;
import static hexlet.code.ElementDiff.CHANGED;
import static hexlet.code.ElementDiff.REMOVED;
import static hexlet.code.ElementDiff.UNCHANGED;

public final class StylishFormatter implements Formatter {
    @Override
    public String format(final Map<String, ElementDiff> diff) throws Exception {
        StringBuilder result = new StringBuilder("{\n");
        for (String key: diff.keySet()) {
            ElementDiff elem = diff.get(key);
            switch (elem.getStatus()) {
                case UNCHANGED -> result.append(makeStylishLine("    ", key, elem.getValue()));
                case ADDED -> result.append(makeStylishLine("  + ", key, elem.getValue()));
                case REMOVED -> result.append(makeStylishLine("  - ", key, elem.getValue()));
                case CHANGED -> {
                    Map<String, Object> value = (Map<String, Object>) elem.getValue();
                    result.append(makeStylishLine("  - ", key, value.get("old")));
                    result.append(makeStylishLine("  + ", key, value.get("new")));
                }
                default -> throw new Exception("Unknown element status: " + elem.getStatus());
            }
        }
        return result.append("}").toString();

    }

    private static String makeStylishLine(String prefix, String key, Object value) {
        value = (value == null) ? "null" : value.toString();
        return prefix + key + ": " + value + "\n";
    }
}
