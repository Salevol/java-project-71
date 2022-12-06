import hexlet.code.Differ;
import hexlet.code.utils.FileHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDiffer {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private final String jsonFilepath1 = "src/test/resources/nested1.json";
    private final String jsonFilepath2 = "src/test/resources/nested2.json";
    private final String yamlFilepath1 = "src/test/resources/nested1.yaml";
    private final String yamlFilepath2 = "src/test/resources/nested2.yaml";

    @BeforeAll
    public static void readFiles() throws Exception {
        expectedStylish = FileHandler.readFile("src/test/resources/expected_stylish");
        expectedPlain = FileHandler.readFile("src/test/resources/expected_plain");
        expectedJson = FileHandler.readFile("src/test/resources/expected_json");
    }

    @Test
    public void testJsonFormatter() throws Exception {
        String actual = Differ.generate(jsonFilepath1, jsonFilepath2, "json");
        assertThat(actual).isEqualTo(expectedJson);
        actual = Differ.generate(yamlFilepath1, yamlFilepath2, "json");
        assertThat(actual).isEqualTo(expectedJson);
        assertThrows(Exception.class, () -> Differ.generate("nofile1.json", "nofile2.json"));
    }

    @Test
    public void testStylishFormatter() throws Exception {
        String actual = Differ.generate(jsonFilepath1, jsonFilepath2);
        assertThat(actual).isEqualTo(expectedStylish);
        actual = Differ.generate(yamlFilepath1, yamlFilepath2);
        assertThat(actual).isEqualTo(expectedStylish);
        actual = Differ.generate(jsonFilepath1, jsonFilepath2, "stylish");
        assertThat(actual).isEqualTo(expectedStylish);
        actual = Differ.generate(yamlFilepath1, yamlFilepath2, "stylish");
        assertThat(actual).isEqualTo(expectedStylish);
    }

    @Test
    public void testPlainFormatter() throws Exception {
        String actual = Differ.generate(jsonFilepath1, jsonFilepath2, "plain");
        assertThat(actual).isEqualTo(expectedPlain);
        actual = Differ.generate(yamlFilepath1, yamlFilepath2, "plain");
        assertThat(actual).isEqualTo(expectedPlain);
    }
}
