package hexlet.code.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    public static String readFile(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath();
        if (!Files.exists(path)) {
            throw new NoSuchFileException(path.toString());
        }
        return Files.readString(path);
    }
}
