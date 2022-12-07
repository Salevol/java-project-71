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
                case UNCHANGED -> result.append(makeStylishLine("    ", key, elem.getOldValue()));
                case ADDED -> result.append(makeStylishLine("  + ", key, elem.getNewValue()));
                case REMOVED -> result.append(makeStylishLine("  - ", key, elem.getOldValue()));
                case CHANGED -> {
                    result.append(makeStylishLine("  - ", key, elem.getOldValue()));
                    result.append(makeStylishLine("  + ", key, elem.getNewValue()));
                }
                default -> throw new Exception("Unknown element status: " + elem.getStatus());
            }
        }
        return result.append("}").toString();

    }

    private static String makeStylishLine(String prefix, String key, Object value) {
        return prefix + key + ": " + value.toString() + "\n";
    }
}
