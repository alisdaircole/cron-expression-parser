package parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static parser.GenerateInteger.getIntegersInRange;
import static parser.GenerateInteger.getIntegersInRangeFromCron;

class GenerateIntegerTest {

    @ParameterizedTest
    @MethodSource("getIntegersInRangeWithValidRangeReturnsExpectedValuesParameters")
    public void getIntegersInRangeWithValidRangeReturnsExpectedValues(int start, int end, List<Integer> expected) {
        List<Integer> actual = getIntegersInRange(start, end)
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getIntegersInRangeWithValidRangeReturnsExpectedValuesParameters() {
        return Stream.of(
                Arguments.of(1, 5, List.of(1, 2, 3, 4, 5)),
                Arguments.of(1, 1, List.of(1)),
                Arguments.of(5, 1, List.of()),
                Arguments.of(0, -10, List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("getIntegersInRangeFromCronWithValidRangeReturnsExpectedValuesParameters")
    public void getIntegersInRangeFromCronWithValidRangeReturnsExpectedValues(String cron, List<Integer> expected) {
        List<Integer> actual = getIntegersInRangeFromCron(cron)
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> getIntegersInRangeFromCronWithValidRangeReturnsExpectedValuesParameters() {
        return Stream.of(
                Arguments.of("1-5", List.of(1, 2, 3, 4, 5)),
                Arguments.of("1-7", List.of(1, 2, 3, 4, 5, 6, 7))
        );
    }
}
