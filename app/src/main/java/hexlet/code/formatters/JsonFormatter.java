package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ElementDiff;
import hexlet.code.Formatter;

import java.util.Map;

public class JsonFormatter implements Formatter {

    @Override
    public String format(Map<String, ElementDiff> diff) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing json diff to String");
        }
    }
}
