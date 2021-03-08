package parser;

import model.Operator;
import model.TimeField;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static validation.Validation.validateIntegerInMinMaxBounds;

public final class GenerateInteger {

    private GenerateInteger() {
    }

    public static Stream<Integer> getIntegersInRangeFromCron(String cron, TimeField timeField) {
        String[] rangeValues = cron.split(Operator.RANGE);

        int start = Integer.parseInt(rangeValues[0]);
        int end = Integer.parseInt(rangeValues[1]);

        validateIntegerInMinMaxBounds(start, timeField);
        validateIntegerInMinMaxBounds(end, timeField);

        return getIntegersInRange(start, end);
    }

    public static Stream<Integer> getIntegersInRange(int startInclusive, int endInclusive) {
        return IntStream.rangeClosed(startInclusive, endInclusive)
                .boxed();
    }
}
