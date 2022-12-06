package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ElementDiff;
import hexlet.code.Formatter;

import java.util.Map;

public final class JsonFormatter implements Formatter {

    @Override
    public String format(Map<String, ElementDiff> diff) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diff);
    }
}
