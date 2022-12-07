package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.ElementDiff.ADDED;
import static hexlet.code.ElementDiff.CHANGED;
import static hexlet.code.ElementDiff.REMOVED;
import static hexlet.code.ElementDiff.UNCHANGED;

public class MapsComparator {
    public static Map<String, ElementDiff> compare(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, ElementDiff> diff = new TreeMap<>();
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        Object oldValue;
        Object newValue;
        String status;
        for (String key: keys) {
            oldValue = checkNullValue(key, map1);
            newValue = checkNullValue(key, map2);
            status = checkStatus(oldValue, newValue);
            diff.put(key, new ElementDiff(oldValue, newValue, status));
        }
        return diff;
    }

    private static Object checkNullValue(String key, Map<String, Object> map) {
        Object value = null;
        if (map.containsKey(key)) {
            value = map.get(key) == null ? "null" : map.get(key);
        }
        return value;
    }

    private static String checkStatus(Object oldValue, Object newValue) {
        if (Objects.equals(oldValue, newValue)) {
            return UNCHANGED;
        } else if (oldValue == null) {
            return ADDED;
        } else if (newValue == null) {
            return REMOVED;
        } else {
            return CHANGED;
        }
    }
}
