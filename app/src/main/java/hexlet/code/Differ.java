package hexlet.code;

import hexlet.code.parser.Parser;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> firstFile = Parser.parse(filePath1);
        Map<String, Object> secondFile = Parser.parse(filePath2);
        String result = "{\n";
        Set<String> keys = new TreeSet<>(firstFile.keySet());
        keys.addAll(secondFile.keySet());
        for (String key: keys) {
            if (firstFile.containsKey(key) && secondFile.containsKey(key)) {
                if (firstFile.get(key).equals(secondFile.get(key))) {
                    result += "  " + key + ": " + firstFile.get(key) + "\n";
                } else {
                    result += "- " + key + ": " + firstFile.get(key) + "\n";
                    result += "+ " + key + ": " + secondFile.get(key) + "\n";
                }
            } else if (firstFile.containsKey(key)) {
                result += "- " + key + ": " + firstFile.get(key) + "\n";
            } else if (secondFile.containsKey(key)) {
                result += "+ " + key + ": " + secondFile.get(key) + "\n";
            }
        }
        return result + "}";
    }
}
