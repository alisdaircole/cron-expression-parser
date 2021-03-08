package output;

import model.CronExpression;

public class CronExpressionConsoleOutput implements CronExpressionOutput {

    @Override
    public void output(CronExpression cronExpression) {
        System.out.println(cronExpression);
    }
}
