package hexlet.code.formatters;

import hexlet.code.ElementDiff;
import hexlet.code.Formatter;

import java.util.Map;

public final class StylishFormatter implements Formatter {
    @Override
    public String format(final Map<String, ElementDiff> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (String key: diff.keySet()) {
            ElementDiff elemDiff = diff.get(key);
            if (!elemDiff.isUpdated()) {
                result.append(makeStylishLine("  ", key, elemDiff.getOldValue()));
            } else if (elemDiff.isAdded()) {
                result.append(makeStylishLine("+ ", key, elemDiff.getNewValue()));
            } else if (elemDiff.isRemoved()) {
                result.append(makeStylishLine("- ", key, elemDiff.getOldValue()));
            } else {
                result.append(makeStylishLine("- ", key, elemDiff.getOldValue()));
                result.append(makeStylishLine("+ ", key, elemDiff.getNewValue()));
            }
        }
        return result.append("}").toString();
    }

    private static String makeStylishLine(String prefix, String key, Object value) {
        return prefix + key + ": " + value.toString() + "\n";
    }
}
