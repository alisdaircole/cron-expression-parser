package parser;

import model.Cron;
import model.Operator;
import model.TimeField;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static parser.GenerateInteger.getIntegersInRange;
import static parser.GenerateInteger.getIntegersInRangeFromCron;
import static validation.Validation.validateCronValue;

public final class ParseCronString {

    private ParseCronString() {
    }

    public static Cron getCron(String cron, TimeField timeField) {
        String[] cronValueArray = cron.split(Operator.VALUES);

        Arrays.stream(cronValueArray)
                .forEach(cronValue -> validateCronValue(cronValue, timeField));

        List<Integer> cronResult = Arrays.stream(cronValueArray)
                .map(cronValue -> parseCronValue(cronValue, timeField))
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return new Cron(cronResult, timeField);
    }

    private static List<Integer> parseCronValue(String cronValue, TimeField timeField) {
        Optional<String[]> incrementValues = splitCronByIncrement(cronValue);
        String cronValueIncrementStripped = incrementValues.isPresent() ? incrementValues.get()[0] : cronValue;

        Stream<Integer> parsedCronValueStream;
        boolean noOperatorCron = false;

        if (cronValueIncrementStripped.equals(Operator.ALL)) {
            parsedCronValueStream = getIntegersInRange(timeField.getMin(), timeField.getMax());
        } else if (cronValueIncrementStripped.contains(Operator.RANGE)) {
            parsedCronValueStream = getIntegersInRangeFromCron(cronValueIncrementStripped);
        } else {
            noOperatorCron = true;
            int cronValueIncrementStrippedInt = Integer.parseInt(cronValueIncrementStripped);
            parsedCronValueStream = Stream.of(cronValueIncrementStrippedInt);
        }

        if (incrementValues.isPresent()) {
            int increment = Integer.parseInt(incrementValues.get()[1]);
            parsedCronValueStream = applyIncrement(parsedCronValueStream, timeField, increment, noOperatorCron);
        }

        return parsedCronValueStream.collect(Collectors.toList());
    }

    private static Optional<String[]> splitCronByIncrement(String cron) {
        return cron.contains(Operator.INCREMENT) ? Optional.of(cron.split(Operator.INCREMENT)) : Optional.empty();
    }

    private static Stream<Integer> applyIncrement(Stream<Integer> cronValues, TimeField timeField, int increment,
                                                  boolean noOperatorCron) {
        if (noOperatorCron) {
            return cronValues.flatMap(start -> getIntegersInRange(start, timeField.getMax()))
                    .filter(val -> val % increment == 0);
        } else {
            return cronValues.filter(val -> val % increment == 0);
        }
    }
}
