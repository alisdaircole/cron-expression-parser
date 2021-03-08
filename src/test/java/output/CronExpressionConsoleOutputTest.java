package output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.TestValues.CRON_EXPRESSION;

class CronExpressionConsoleOutputTest {

    private final ByteArrayOutputStream outputCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputCaptor));
    }

    @Test
    public void printOutputsTextInExpectedFormat() {
        CronExpressionOutput cronExpressionOutput = new CronExpressionConsoleOutput();
        cronExpressionOutput.output(CRON_EXPRESSION);

        String expectedOutput = """
                minute        0 15 30 45
                hour          0
                day of month  1 15
                month         1 2 3 4 5 6 7 8 9 10 11 12
                day of week   1 2 3 4 5
                command       /usr/bin/find
                """;
        String actualOutput = outputCaptor.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}
