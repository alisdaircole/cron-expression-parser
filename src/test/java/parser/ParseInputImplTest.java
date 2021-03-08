package parser;

import exception.InvalidCronException;
import model.CronExpression;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static util.TestValues.CRON_EXPRESSION;
import static util.TestValues.INVALID_INPUT;
import static util.TestValues.VALID_INPUT;

class ParseInputImplTest {

    @ParameterizedTest()
    @ValueSource(strings = {INVALID_INPUT, ""})
    public void getCronExpressionWithInvalidInputThrowsException() {
        ParseInput parseInput = new ParseInputImpl();

        InvalidCronException exception = assertThrows(InvalidCronException.class,
                () -> parseInput.getCronExpression(INVALID_INPUT));

        String expectedExceptionMessage = "Invalid input, should consist of five whitespace separated time fields " +
                "(minute, hour, day of month, month, and day of week), plus a command";
        String actualExceptionMessage = exception.getMessage();

        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    public void getCronExpressionWithValidInputReturnsExpectedValue() {
        ParseInput parseInput = new ParseInputImpl();

        CronExpression expected = CRON_EXPRESSION;
        CronExpression actual = parseInput.getCronExpression(VALID_INPUT);

        assertEquals(expected, actual);
    }
}
