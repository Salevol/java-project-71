import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDiffer {
    public static final String JSON_FILEPATH1 = "src/test/resources/file1.json";
    public static final String JSON_FILEPATH2 = "src/test/resources/file2.json";
    public static final String YAML_FILEPATH1 = "src/test/resources/file1.yaml";
    public static final String YAML_FILEPATH2 = "src/test/resources/file2.yaml";
    @Test
    public void testGenerateFromJson() throws IOException {
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

    @Test
    public void testGenerateFromYaml() throws IOException {
        String actual = Differ.generate(YAML_FILEPATH1, YAML_FILEPATH2);
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
