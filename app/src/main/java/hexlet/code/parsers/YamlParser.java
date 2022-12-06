package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.Parser;

import java.util.Map;

public final class YamlParser implements Parser {
    @Override
    public Map<String, Object> parse(String content) throws JsonProcessingException {
        ObjectMapper yamlMapper = new YAMLMapper();
        return yamlMapper.readValue(content, Map.class);
    }
}
