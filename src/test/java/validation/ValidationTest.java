package validation;

import exception.InvalidCronException;
import model.TimeField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static util.TestValues.INVALID_INPUT;
import static util.TestValues.VALID_INPUT;
import static validation.Validation.validateCronValue;
import static validation.Validation.validateInputSize;
import static validation.Validation.validateIntegerInMinMaxBounds;

class ValidationTest {

    @Test
    public void validateInputSizeWithValidInputCompletesSuccessfully() {
        String[] validInputSplit = VALID_INPUT.split(" ");

        validateInputSize(validInputSplit);
    }

    @Test
    public void validateInputSizeWithInvalidInputThrowsException() {
        String[] invalidInputSplit = INVALID_INPUT.split(" ");

        InvalidCronException exception = assertThrows(InvalidCronException.class,
                () -> validateInputSize(invalidInputSplit));

        String expectedExceptionMessage = "Invalid input, should consist of five whitespace separated time fields " +
                "(minute, hour, day of month, month, and day of week), plus a command";
        String actualExceptionMessage = exception.getMessage();

        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @ParameterizedTest
    @MethodSource("validateCronValueWithNoOperatorCompletesSuccessfullyParameters")
    public void validateCronValueWithNoOperatorCompletesSuccessfully(String cronValue, TimeField timeField) {
        validateCronValue(cronValue, timeField);
    }

    private static Stream<Arguments> validateCronValueWithNoOperatorCompletesSuccessfullyParameters() {
        return Stream.of(
                Arguments.of("1", TimeField.MINUTE),
                Arguments.of("10", TimeField.MINUTE),
                Arguments.of("0", TimeField.MINUTE),
                Arguments.of("59", TimeField.MINUTE)
        );
    }

    @ParameterizedTest
    @MethodSource("validateCronValueWithRangeOperatorCompletesSuccessfullyParameters")
    public void validateCronValueWithRangeOperatorCompletesSuccessfully(String cronValue, TimeField timeField) {
        validateCronValue(cronValue, timeField);
    }

    private static Stream<Arguments> validateCronValueWithRangeOperatorCompletesSuccessfullyParameters() {
        return Stream.of(
                Arguments.of("1-5", TimeField.MINUTE),
                Arguments.of("1-59", TimeField.MINUTE),
                Arguments.of("31-31", TimeField.DAY_OF_MONTH),
                Arguments.of("1-20/5", TimeField.MINUTE)
        );
    }

    @ParameterizedTest
    @MethodSource("validateCronValueWithAllOperatorCompletesSuccessfullyParameters")
    public void validateCronValueWithAllOperatorCompletesSuccessfully(String cronValue, TimeField timeField) {
        validateCronValue(cronValue, timeField);
    }

    private static Stream<Arguments> validateCronValueWithAllOperatorCompletesSuccessfullyParameters() {
        return Stream.of(
                Arguments.of("*", TimeField.HOUR),
                Arguments.of("*/5", TimeField.HOUR)
        );
    }

    @ParameterizedTest
    @MethodSource("validateCronValueWithIncrementOperatorCompletesSuccessfullyParameters")
    public void validateCronValueWithIncrementOperatorCompletesSuccessfully(String cronValue, TimeField timeField) {
        validateCronValue(cronValue, timeField);
    }

    private static Stream<Arguments> validateCronValueWithIncrementOperatorCompletesSuccessfullyParameters() {
        return Stream.of(
                Arguments.of("10/5", TimeField.HOUR),
                Arguments.of("10-40/10", TimeField.MINUTE),
                Arguments.of("*/10", TimeField.DAY_OF_MONTH)
        );
    }

    @ParameterizedTest
    @MethodSource("validateCronValueWithInvalidCronThrowsExceptionParameters")
    public void validateCronValueWithInvalidCronThrowsException(String cronValue, TimeField timeField,
                                                                String expectedExceptionMessage) {
        InvalidCronException exception = assertThrows(InvalidCronException.class,
                () ->  validateCronValue(cronValue, timeField));

        String actualExceptionMessage = exception.getMessage();

        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    private static Stream<Arguments> validateCronValueWithInvalidCronThrowsExceptionParameters() {
        return Stream.of(
                Arguments.of("-1", TimeField.HOUR, "The following cron for time field hour is invalid: -1"),
                Arguments.of("-1-5", TimeField.DAY_OF_WEEK,
                        "The following cron for time field day of week is invalid: -1-5"),
                Arguments.of("10/*", TimeField.HOUR, "The following cron for time field hour is invalid: 10/*"),
                Arguments.of("10/", TimeField.HOUR, "The following cron for time field hour is invalid: 10/"),
                Arguments.of("10-", TimeField.MONTH, "The following cron for time field month is invalid: 10-"),
                Arguments.of("", TimeField.MONTH, "The following cron for time field month is invalid: ")
        );
    }

    @ParameterizedTest
    @MethodSource("validateIntegerInMinMaxBoundsWithValidIntegerCompletesSuccessfullyParameters")
    public void validateIntegerInMinMaxBoundsWithValidIntegerCompletesSuccessfully(int i, TimeField timeField) {
        validateIntegerInMinMaxBounds(i, timeField);
    }

    private static Stream<Arguments> validateIntegerInMinMaxBoundsWithValidIntegerCompletesSuccessfullyParameters() {
        return Stream.of(
                Arguments.of(0, TimeField.HOUR),
                Arguments.of(23, TimeField.HOUR)
        );
    }

    @ParameterizedTest
    @MethodSource("validateIntegerInMinMaxBoundsWithInvalidIntegerThrowsExceptionParameters")
    public void validateIntegerInMinMaxBoundsWithInvalidIntegerThrowsException(int i, TimeField timeField,
                                                                               String expectedExceptionMessage) {
        InvalidCronException exception = assertThrows(InvalidCronException.class,
                () ->  validateIntegerInMinMaxBounds(i, timeField));

        String actualExceptionMessage = exception.getMessage();

        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    private static Stream<Arguments> validateIntegerInMinMaxBoundsWithInvalidIntegerThrowsExceptionParameters() {
        return Stream.of(
                Arguments.of(0, TimeField.DAY_OF_WEEK, "Value: 0 is out of bounds for day of week time field"),
                Arguments.of(8, TimeField.DAY_OF_WEEK, "Value: 8 is out of bounds for day of week time field"),
                Arguments.of(100, TimeField.DAY_OF_WEEK, "Value: 100 is out of bounds for day of week time field"),
                Arguments.of(-1, TimeField.DAY_OF_WEEK, "Value: -1 is out of bounds for day of week time field")
        );
    }
}
