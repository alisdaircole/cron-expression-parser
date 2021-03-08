import model.CronExpression;
import output.CronExpressionConsoleOutput;
import output.CronExpressionOutput;
import parser.ParseInput;
import parser.ParseInputImpl;

public class Main {

    public static void main(String[] args) {
        String cron = args[0];

        ParseInput parseInput = new ParseInputImpl();
        CronExpression cronExpression = parseInput.getCronExpression(cron);

        CronExpressionOutput cronExpressionOutput = new CronExpressionConsoleOutput();
        cronExpressionOutput.output(cronExpression);
    }
}
