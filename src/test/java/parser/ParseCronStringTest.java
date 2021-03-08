package parser;

import exception.InvalidCronException;
import model.Cron;
import model.TimeField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static parser.ParseCronString.getCron;

class ParseCronStringTest {

    @Test
    public void getCronWithInvalidStringThrowsException() {
        String invalidCronString = "1,2,3-";
        TimeField timeField = TimeField.MINUTE;

        InvalidCronException exception = assertThrows(InvalidCronException.class,
                () -> getCron(invalidCronString, timeField));

        String expectedExceptionMessage = "The following cron for time field minute is invalid: 3-";
        String actualExceptionMessage = exception.getMessage();

        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @ParameterizedTest
    @MethodSource("getCronWithIntegerStringReturnsExpectedValueParameters")
    public void getCronWithIntegerStringReturnsExpectedValue(String cron, TimeField timeField, Cron expected) {
        Cron actual = getCron(cron, timeField);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getCronWithIntegerStringReturnsExpectedValueParameters() {
        return Stream.of(
                Arguments.of("1", TimeField.MINUTE, new Cron(List.of(1), TimeField.MINUTE)),
                Arguments.of("59", TimeField.MINUTE, new Cron(List.of(59), TimeField.MINUTE))
        );
    }

    @ParameterizedTest
    @MethodSource("getCronWithAllOperatorStringReturnsExpectedValueParameters")
    public void getCronWithAllOperatorStringReturnsExpectedValue(String cron, TimeField timeField, Cron expected) {
        Cron actual = getCron(cron, timeField);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getCronWithAllOperatorStringReturnsExpectedValueParameters() {
        return Stream.of(
                Arguments.of("*", TimeField.DAY_OF_WEEK, new Cron(List.of(1, 2, 3, 4, 5, 6, 7), TimeField.DAY_OF_WEEK)),
                Arguments.of("*/20", TimeField.MINUTE, new Cron(List.of(0, 20, 40), TimeField.MINUTE))
        );
    }

    @ParameterizedTest
    @MethodSource("getCronWithRangeOperatorStringReturnsExpectedValueParameters")
    public void getCronWithRangeOperatorStringReturnsExpectedValue(String cron, TimeField timeField, Cron expected) {
        Cron actual = getCron(cron, timeField);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getCronWithRangeOperatorStringReturnsExpectedValueParameters() {
        return Stream.of(
                Arguments.of("1-7", TimeField.DAY_OF_WEEK, new Cron(List.of(1, 2, 3, 4, 5, 6, 7),
                        TimeField.DAY_OF_WEEK)),
                Arguments.of("1-1", TimeField.MINUTE, new Cron(List.of(1), TimeField.MINUTE)),
                Arguments.of("1-20/5", TimeField.MINUTE, new Cron(List.of(5, 10, 15, 20), TimeField.MINUTE)),
                Arguments.of("20-5", TimeField.MINUTE, new Cron(List.of(), TimeField.MINUTE)),
                Arguments.of("1-3,5-8", TimeField.DAY_OF_MONTH, new Cron(List.of(1, 2, 3, 5, 6, 7, 8),
                        TimeField.DAY_OF_MONTH))
        );
    }

    @ParameterizedTest
    @MethodSource("getCronWithValuesOperatorStringReturnsExpectedValueParameters")
    public void getCronWithValuesOperatorStringReturnsExpectedValue(String cron, TimeField timeField, Cron expected) {
        Cron actual = getCron(cron, timeField);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getCronWithValuesOperatorStringReturnsExpectedValueParameters() {
        return Stream.of(
                Arguments.of("1,20,3", TimeField.DAY_OF_MONTH, new Cron(List.of(1, 3, 20), TimeField.DAY_OF_MONTH)),
                Arguments.of("*/10,3,4", TimeField.DAY_OF_MONTH, new Cron(List.of(3, 4, 10, 20, 30),
                        TimeField.DAY_OF_MONTH)),
                Arguments.of("3,5-8,11", TimeField.MONTH, new Cron(List.of(3, 5, 6, 7, 8, 11), TimeField.MONTH)),
                Arguments.of("1,10/5", TimeField.DAY_OF_MONTH, new Cron(List.of(1, 10, 15, 20, 25, 30),
                        TimeField.DAY_OF_MONTH)),
                Arguments.of("1,10/5,28-30", TimeField.DAY_OF_MONTH, new Cron(List.of(1, 10, 15, 20, 25, 28, 29, 30),
                        TimeField.DAY_OF_MONTH))
        );
    }

    @ParameterizedTest
    @MethodSource("getCronWithIncrementOperatorStringReturnsExpectedValueParameters")
    public void getCronWithIncrementOperatorStringReturnsExpectedValue(String cron, TimeField timeField, Cron expected) {
        Cron actual = getCron(cron, timeField);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getCronWithIncrementOperatorStringReturnsExpectedValueParameters() {
        return Stream.of(
                Arguments.of("20/15", TimeField.MINUTE, new Cron(List.of(30, 45), TimeField.MINUTE)),
                Arguments.of("1-1/15", TimeField.MINUTE, new Cron(List.of(), TimeField.MINUTE)),
                Arguments.of("10-40/10", TimeField.MINUTE, new Cron(List.of(10, 20, 30, 40), TimeField.MINUTE)),
                Arguments.of("*/10", TimeField.MINUTE, new Cron(List.of(0, 10, 20, 30, 40, 50), TimeField.MINUTE)),
                Arguments.of("1,2,30/10", TimeField.MINUTE, new Cron(List.of(1, 2, 30, 40, 50), TimeField.MINUTE)),
                Arguments.of("*/22,*/9", TimeField.MINUTE, new Cron(List.of(0, 9, 18, 22, 27, 36, 44, 45, 54),
                        TimeField.MINUTE))
        );
    }
}
