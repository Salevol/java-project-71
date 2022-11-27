import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDiffer {
    public static final String JSON_FILEPATH1 = "src/test/resources/file1.json";
    public static final String JSON_FILEPATH2 = "src/test/resources/file2.json";

    @Test
    public void testGenerate() throws IOException {
        String actual = Differ.generate(JSON_FILEPATH1, JSON_FILEPATH2);
        String expected =  """
{
- follow: false
  host: hexlet.io
- proxy: 123.234.53.22
- timeout: 50
+ timeout: 20
+ verbose: true
}               """;
        assertThat(actual).isEqualTo(expected);
    }
}
