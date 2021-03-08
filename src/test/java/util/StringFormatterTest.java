package util;

import model.TimeField;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringFormatterTest {

    @ParameterizedTest
    @MethodSource("getStringWithWhitespacePaddingReturnsExpectedValueParameters")
    public void getStringWithWhitespacePaddingReturnsExpectedValue(String fieldName, String fieldValue,
                                                                   String expected) {
        String actual = StringFormatter.getStringWithWhitespacePadding(fieldName, fieldValue);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getStringWithWhitespacePaddingReturnsExpectedValueParameters() {
        return Stream.of(
                Arguments.of(TimeField.MINUTE.toString(), "1 2 30", "minute        1 2 30"),
                Arguments.of(TimeField.DAY_OF_WEEK.toString(), "1 2 3 4 5 6 7", "day of week   1 2 3 4 5 6 7")
        );
    }
}
