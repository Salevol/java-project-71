package hexlet.code.formatters;

import hexlet.code.Formatter;

public class FormatFactory {

    public static Formatter getFormatter(String format) {
        return switch (format) {
            case "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            default -> throw new RuntimeException("Illegal format");
        };
    }

}
