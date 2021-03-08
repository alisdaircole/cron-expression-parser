package validation;

import exception.InvalidCronException;

public final class Validation {

    private Validation() {
    }

    public static void validateInputSize(String[] cronValues) {
        if (cronValues.length != 6) {
            throw new InvalidCronException("Invalid input, should consist of five whitespace separated time fields " +
                    "(minute, hour, day of month, month, and day of week), plus a command");
        }
    }
}
