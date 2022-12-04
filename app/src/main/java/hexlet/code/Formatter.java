package hexlet.code;

import java.util.Map;

public interface Formatter {

    String format(Map<String, ElementDiff> diff);
}
