package parser;

import exception.InvalidCronException;
import model.TimeField;
import org.junit.jupiter.api.Test;

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
}
