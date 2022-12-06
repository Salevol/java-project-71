package hexlet.code.utils;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    public static String readFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath();
        if (!Files.exists(path)) {
            throw new NoSuchFileException(path.toString());
        }
        return Files.readString(path);
    }

    public static String getFileFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }
}
