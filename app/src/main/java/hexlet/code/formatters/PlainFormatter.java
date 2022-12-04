package hexlet.code.formatters;

import hexlet.code.ElementDiff;
import hexlet.code.Formatter;
import org.apache.commons.lang3.ClassUtils;

import java.util.Map;

public final class PlainFormatter implements Formatter {
    private static final String ADDED = "Property '%s' was added with value: %s\n";
    private static final String REMOVED = "Property '%s' was removed\n";
    private static final String UPDATED = "Property '%s' was updated. From %s to %s\n";



    @Override
    public String format(final Map<String, ElementDiff> diff) {
        StringBuilder result = new StringBuilder();
        for (String key: diff.keySet()) {
            ElementDiff elem = diff.get(key);
            if (elem.isUpdated() && elem.isAdded()) {
                result.append(String.format(ADDED, key, checkObj(elem.getNewValue())));
            } else if (elem.isRemoved()) {
                result.append(String.format(REMOVED, key, checkObj(elem.getOldValue())));
            } else if (elem.isUpdated()) {
                result.append(String.format(UPDATED, key, checkObj(elem.getOldValue()), checkObj(elem.getNewValue())));
            }
        }
        return result.toString();
    }

    private static String checkObj(Object value) {
        if (value == null || value == "null") {
            return null;
        }
        if (ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
            return value.toString();
        }
        return (value instanceof String) ? "'" + value.toString() + "'" : "[complex value]";
    }
}
