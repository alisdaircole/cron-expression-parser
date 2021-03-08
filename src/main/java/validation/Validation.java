package validation;

import exception.InvalidCronException;
import model.TimeField;

public final class Validation {

    private static final String CRON_VALUE_VALIDATION_PATTERN = "^((\\d+(-\\d+)?)|\\*)(/\\d+)?$";

    private Validation() {
    }

    public static void validateInputSize(String[] cronValues) {
        if (cronValues.length != 6) {
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
}
