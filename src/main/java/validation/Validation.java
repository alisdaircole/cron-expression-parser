package validation;

import exception.InvalidCronException;
import model.TimeField;

public final class Validation {

    private static final int NUMBER_OF_TIME_FIELDS_PLUS_COMMAND_FIELD = TimeField.values().length + 1;
    private static final String CRON_VALUE_VALIDATION_PATTERN = "^((\\d+(-\\d+)?)|\\*)(/\\d+)?$";

    private Validation() {
    }

    public static void validateInputSize(String[] cronValues) {
        if (cronValues.length != NUMBER_OF_TIME_FIELDS_PLUS_COMMAND_FIELD) {
            throw new InvalidCronException("Invalid input, should consist of five whitespace separated time fields " +
                    "(minute, hour, day of month, month, and day of week), plus a command");
        }
    }

    public static void validateCronValue(String cronValue, TimeField timeField) {
        if (!cronValue.matches(CRON_VALUE_VALIDATION_PATTERN)) {
            throw new InvalidCronException("The following cron for time field " + timeField.toString() +
                    " is invalid: " + cronValue);
        }
    }

    public static void validateIntegerInMinMaxBounds(int i, TimeField timeField) {
        if(timeField.isOutOfBounds(i)) {
            throw new InvalidCronException("Value: " + i + " is out of bounds for " + timeField.toString()
                    + " time field");
        }
    }
}
