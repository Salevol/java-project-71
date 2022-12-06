package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Parser;

import java.util.Map;

public final class JsonParser implements Parser {

    @Override
    public Map<String, Object> parse(String content) throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.readValue(content, Map.class);
    }
}
