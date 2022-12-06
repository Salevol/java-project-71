package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CompareMaps {
    public static final Map<String, ElementDiff> compare(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, ElementDiff> diff = new TreeMap<>();
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        for (String key: keys) {
            diff.put(key, new ElementDiff(checkNullValue(key, map1), checkNullValue(key, map2)));
        }
        return diff;
    }

    private static Object checkNullValue(String key, Map map) {
        Object value = null;
        if (map.containsKey(key)) {
            value = map.get(key) == null ? "null" : map.get(key);
        }
        return value;
    }
}
