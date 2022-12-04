package hexlet.code;

import hexlet.code.formatters.FormatFactory;
import hexlet.code.parser.Parser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Map<String, Object> firstFile = new LinkedHashMap<>(Parser.parse(filePath1));
        Map<String, Object> secondFile = new LinkedHashMap<>(Parser.parse(filePath2));
        Map<String, ElementDiff> diff = new TreeMap<>();
        Set<String> keys = new TreeSet<>(firstFile.keySet());
        keys.addAll(secondFile.keySet());
        for (String key: keys) {
            diff.put(key, new ElementDiff(checkNullValue(key, firstFile), checkNullValue(key, secondFile)));
        }
        Formatter formatter = FormatFactory.getFormatter(format);
        return formatter.format(diff);
    }
    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    private static Object checkNullValue(String key, Map map) {
        Object value = null;
        if (map.containsKey(key)) {
            value = map.get(key) == null ? "null" : map.get(key);
        }
        return value;
    }
}
