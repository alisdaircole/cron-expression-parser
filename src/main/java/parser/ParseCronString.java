package parser;

import model.Cron;
import model.Operator;
import model.TimeField;

import java.util.Arrays;

import static validation.Validation.validateCronValue;

public final class ParseCronString {

    private ParseCronString() {
    }

    public static Cron getCron(String cron, TimeField timeField) {
        String[] cronValueArray = cron.split(Operator.VALUES);

        Arrays.stream(cronValueArray)
                .forEach(cronValue -> validateCronValue(cronValue, timeField));

        return null;
    }
}
