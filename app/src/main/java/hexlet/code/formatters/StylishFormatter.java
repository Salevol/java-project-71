package hexlet.code.formatters;

import hexlet.code.ElementDiff;
import hexlet.code.Formatter;

import java.util.Map;

public final class StylishFormatter implements Formatter {
    @Override
    public String format(final Map<String, ElementDiff> diff) throws Exception {
        StringBuilder result = new StringBuilder("{\n");
        for (String key: diff.keySet()) {
            ElementDiff elem = diff.get(key);
            switch (elem.getStatus()) {
                case "unchanged":
                    result.append(makeStylishLine("    ", key, elem.getOldValue()));
                    break;
                case "added":
                    result.append(makeStylishLine("  + ", key, elem.getNewValue()));
                    break;
                case "removed":
                    result.append(makeStylishLine("  - ", key, elem.getOldValue()));
                    break;
                case "changed":
                    result.append(makeStylishLine("  - ", key, elem.getOldValue()));
                    result.append(makeStylishLine("  + ", key, elem.getNewValue()));
                    break;
                default:
                    throw new Exception("Unknown element status: " + elem.getStatus());
            }
        }
        return result.append("}").toString();

    }

    private static String makeStylishLine(String prefix, String key, Object value) {
        return prefix + key + ": " + value.toString() + "\n";
    }
}
