package Step1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class StringConverterTest {

    private static StringConverter stringConverter = new StringConverter();
    private static String REGEX = " ";

    private static String USER_INPUT = "1 + 2 * 3 - 4 / 1";

    @Test
    void 문자열_분해_테스트() {
        List<String> expected = Arrays.asList(USER_INPUT.split(REGEX));

        List<String> result = stringConverter.convertToList(USER_INPUT);
        assertThat(result).isEqualTo(expected);
    }

}