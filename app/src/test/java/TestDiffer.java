import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDiffer {
    public static final String JSON_FILEPATH1 = "src/test/resources/nested1.json";
    public static final String JSON_FILEPATH2 = "src/test/resources/nested2.json";
    public static final String YAML_FILEPATH1 = "src/test/resources/nested1.yaml";
    public static final String YAML_FILEPATH2 = "src/test/resources/nested2.yaml";
    public static final String expectedStylish = """
{
  chars1: [a, b, c]
- chars2: [d, e, f]
+ chars2: false
- checked: false
+ checked: true
- default: null
+ default: [value1, value2]
- id: 45
+ id: null
- key1: value1
+ key2: value2
  numbers1: [1, 2, 3, 4]
- numbers2: [2, 3, 4, 5]
+ numbers2: [22, 33, 44, 55]
- numbers3: [3, 4, 5]
+ numbers4: [4, 5, 6]
+ obj1: {nestedKey=value, isNested=true}
- setting1: Some value
+ setting1: Another value
- setting2: 200
+ setting2: 300
- setting3: true
+ setting3: none
}       """;

    @Test
    public void testGenerateFromJson() throws IOException {
        String actual = Differ.generate(JSON_FILEPATH1, JSON_FILEPATH2);
        assertThat(actual).isEqualTo(expectedStylish);
    }

    @Test
    public void testGenerateFromYaml() throws IOException {
        String actual = Differ.generate(YAML_FILEPATH1, YAML_FILEPATH2);
        assertThat(actual).isEqualTo(expectedStylish);
    }
}
