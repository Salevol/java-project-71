package hexlet.code;

import hexlet.code.parser.Parser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatter) throws IOException {
        Map<String, Object> firstFile = new LinkedHashMap<>(Parser.parse(filePath1));
        Map<String, Object> secondFile = new LinkedHashMap<>(Parser.parse(filePath2));
        Map<String, ElementDiff> diff = new TreeMap<>();
        Set<String> keys = new TreeSet<>(firstFile.keySet());
        keys.addAll(secondFile.keySet());
        String oldValue;
        String newValue;
        for (String key: keys) {
            if (firstFile.containsKey(key)) {
                oldValue = firstFile.get(key) == null ? "null" : firstFile.get(key).toString();
            } else {
                oldValue = null;
            }
            if (secondFile.containsKey(key)) {
                newValue = secondFile.get(key) == null ? "null" : secondFile.get(key).toString();
            } else {
                newValue = null;
            }
            diff.put(key, new ElementDiff(oldValue, newValue));
        }

        return buildStylishString(diff);
    }
    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String buildStylishString(final Map<String, ElementDiff> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (String key: diff.keySet()) {
            ElementDiff elemDiff = diff.get(key);
            if (!elemDiff.changed) {
                result.append(makeStylishLine("  ", key, elemDiff.oldValue));
            } else if (elemDiff.added) {
                result.append(makeStylishLine("+ ", key, elemDiff.newValue));
            } else if (elemDiff.deleted) {
                result.append(makeStylishLine("- ", key, elemDiff.oldValue));
            } else {
                result.append(makeStylishLine("- ", key, elemDiff.oldValue));
                result.append(makeStylishLine("+ ", key, elemDiff.newValue));
            }
        }
        return result.append("}").toString();
    }

    private static class ElementDiff {
        private final Boolean changed;
        private final Boolean deleted;
        private final Boolean added;
        private final Object oldValue;
        private final Object newValue;

        ElementDiff(Object firstValue, Object secondValue) {
            this.oldValue = firstValue;
            this.newValue = secondValue;
            this.changed = !Objects.equals(oldValue, newValue);
            this.deleted = (newValue == null);
            this.added = (oldValue == null);
        }
    }

    private static String makeStylishLine(String prefix, String key, Object value) {
        return prefix + key + ": " + value + "\n";
    }

}
