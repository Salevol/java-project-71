import hexlet.code.Differ;
import hexlet.code.utils.FileHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestDiffer {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    static void readFiles() throws Exception {
        expectedStylish = FileHandler.readFile("src/test/resources/expected_stylish");
        expectedPlain = FileHandler.readFile("src/test/resources/expected_plain");
        expectedJson = FileHandler.readFile("src/test/resources/expected_json");
    }

    @Test
    void testWrongFilepath() {
        assertThrows(Exception.class, () -> Differ.generate("noFile1.json", "noFile2.json"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = "src/test/resources/nested1." + format;
        String filePath2 = "src/test/resources/nested2." + format;

        // Тестируем вызов метода с каждым из фоматерров, а также вызов с форматером по умолчанию
        assertThat(Differ.generate(filePath1, filePath2))
                .isEqualTo(expectedStylish);

        assertThat(Differ.generate(filePath1, filePath2, "stylish"))
                .isEqualTo(expectedStylish);

        assertThat(Differ.generate(filePath1, filePath2, "plain"))
                .isEqualTo(expectedPlain);

        assertThat(Differ.generate(filePath1, filePath2, "json"))
                .isEqualTo(expectedJson);
    }
}
