package hexlet.code.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

import static hexlet.code.utils.FileHandler.readFile;

public class Parser {
    public static Map parse(String filepath) throws IOException {
        String extension = filepath.substring(filepath.lastIndexOf(".") + 1);
        switch (extension) {
            case "yml", "yaml" -> {
                ObjectMapper yamlMapper = new YAMLMapper();
                return yamlMapper.readValue(readFile(filepath), Map.class);
            }
            case "json" -> {
                ObjectMapper jsonMapper = new ObjectMapper();
                return jsonMapper.readValue(readFile(filepath), Map.class);
            }
            default -> throw new IllegalStateException("Unexpected file extension: " + extension);
        }
    }
}
