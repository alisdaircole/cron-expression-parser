package parser;

import model.Operator;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class GenerateInteger {

    private GenerateInteger() {
    }

    public static Stream<Integer> getIntegersInRangeFromCron(String cron) {
        String[] rangeValues = cron.split(Operator.RANGE);

        int start = Integer.parseInt(rangeValues[0]);
        int end = Integer.parseInt(rangeValues[1]);

        return getIntegersInRange(start, end);
    }

    public static Stream<Integer> getIntegersInRange(int startInclusive, int endInclusive) {
        return IntStream.rangeClosed(startInclusive, endInclusive)
                .boxed();
    }
}
