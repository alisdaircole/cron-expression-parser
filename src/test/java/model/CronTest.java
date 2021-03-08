package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestValues.MINUTE;

class CronTest {

    @Test
    public void toStringReturnsExpectedFormat() {
        String expectedString = "minute        0 15 30 45";
        String actualString = MINUTE.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    public void toStringReturnsExpectedFormatForCronWithNoValues() {
        Cron hour = new Cron(List.of(), TimeField.HOUR);

        String expectedString = "hour";
        String actualString = hour.toString();

        assertEquals(expectedString, actualString);
    }
}
