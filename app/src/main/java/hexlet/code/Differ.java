package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.utils.FileHandler.readFile;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json1 = mapper.readValue(readFile(filePath1), Map.class);
        Map<String, Object> json2 = mapper.readValue(readFile(filePath2), Map.class);
        String result = "{\n";
        Set<String> keys = new TreeSet<>(json1.keySet());
        keys.addAll(json2.keySet());
        for (String key: keys) {
            if (json1.containsKey(key) && json2.containsKey(key)) {
                if (json1.get(key).equals(json2.get(key))) {
                    result += "  " + key + ": " + json1.get(key) + "\n";
                } else {
                    result += "- " + key + ": " + json1.get(key) + "\n";
                    result += "+ " + key + ": " + json2.get(key) + "\n";
                }
            } else if (json1.containsKey(key)) {
                result += "- " + key + ": " + json1.get(key) + "\n";
            } else if (json2.containsKey(key)) {
                result += "+ " + key + ": " + json2.get(key) + "\n";
            }
        }
        return result + "}";
    }
}
