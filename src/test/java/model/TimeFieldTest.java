package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeFieldTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 0, 59})
    public void isOutOfBoundsReturnsFalseForIntInBounds(int i) {
        boolean expected = false;
        boolean actual = TimeField.MINUTE.isOutOfBounds(i);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, -1, 60})
    public void isOutOfBoundsReturnsTrueForIntOutOfBounds(int i) {
        boolean expected = true;
        boolean actual = TimeField.MINUTE.isOutOfBounds(i);

        assertEquals(expected, actual);
    }

    @Test
    public void toStringReturnsCorrectlyFormattedString() {
        String expected = "day of month";
        String actual = TimeField.DAY_OF_MONTH.toString();

        assertEquals(expected, actual);
    }
}
