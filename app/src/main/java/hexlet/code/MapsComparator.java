package hexlet.code;

import java.util.LinkedHashMap;
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
        String status;
        for (String key: keys) {
            status = checkStatus(key, map1, map2);
            switch (status) {
                case UNCHANGED, REMOVED -> diff.put(key, new ElementDiff(map1.get(key), status));
                case ADDED -> diff.put(key, new ElementDiff(map2.get(key), status));
                case CHANGED -> {
                    Map<String, Object> value = new LinkedHashMap<>();
                    value.put("old", map1.get(key));
                    value.put("new", map2.get(key));
                    diff.put(key, new ElementDiff(value, status));
                }
                default -> throw new RuntimeException("Unknown status:" + status);
            }
        }
        return diff;
    }

    private static String checkStatus(String key, Map<String, Object> map1, Map<String, Object> map2) {
        if (map1.containsKey(key) && map2.containsKey(key) && Objects.equals(map1.get(key), map2.get(key))) {
            return UNCHANGED;
        } else if (map1.containsKey(key) && map2.containsKey(key)) {
            return CHANGED;
        } else if (map1.containsKey(key)) {
            return REMOVED;
        } else {
            return ADDED;
        }
    }
}
