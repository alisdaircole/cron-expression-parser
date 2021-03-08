package validation;

import exception.InvalidCronException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static util.TestValues.INVALID_INPUT;
import static util.TestValues.VALID_INPUT;
import static validation.Validation.validateInputSize;

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
}
