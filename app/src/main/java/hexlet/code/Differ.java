package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;

import static hexlet.code.formatters.FormatFactory.getFormatter;
import static hexlet.code.parsers.ParserFactory.getParser;
import static hexlet.code.utils.FileHandler.getFileFormat;
import static hexlet.code.utils.FileHandler.readFile;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String ext1 = getFileFormat(filePath1);
        String ext2 = getFileFormat(filePath2);
        String content1 = readFile(filePath1);
        String content2 = readFile(filePath2);
        Parser parser1 = getParser(ext1);
        Parser parser2 = getParser(ext2);
        Map<String, Object> firstFile = new LinkedHashMap<>(parser1.parse(content1));
        Map<String, Object> secondFile = new LinkedHashMap<>(parser2.parse(content2));
        Map<String, ElementDiff> diff = CompareMaps.compare(firstFile, secondFile);
        Formatter formatter = getFormatter(format);
        return formatter.format(diff);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}


