package hexlet.code.parsers;

import hexlet.code.Parser;

public class ParserFactory {
    public static Parser getParser(String extension) {
        return switch (extension) {
            case "yml", "yaml" -> new YamlParser();
            case "json" -> new JsonParser();
            default -> throw new IllegalStateException("Unexpected file extension: " + extension);
        };
    }
}
