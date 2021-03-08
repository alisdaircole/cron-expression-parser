package util;

import model.Cron;
import model.CronExpression;
import model.TimeField;

import java.util.List;

public class TestValues {

    public static final String VALID_INPUT = "*/15 0 1,15 * 1-5 /usr/bin/find";
    public static final String INVALID_INPUT = "0 0";

    public static final Cron MINUTE = new Cron(List.of(0, 15, 30, 45), TimeField.MINUTE);
    public static final Cron HOUR = new Cron(List.of(0), TimeField.HOUR);
    public static final Cron DAY_OF_MONTH = new Cron(List.of(1, 15), TimeField.DAY_OF_MONTH);
    public static final Cron MONTH = new Cron(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), TimeField.MONTH);
    public static final Cron DAY_OF_WEEK = new Cron(List.of(1, 2, 3, 4, 5), TimeField.DAY_OF_WEEK);
    public static final String COMMAND = "/usr/bin/find";

    public static final CronExpression CRON_EXPRESSION = new CronExpression(
            MINUTE,
            HOUR,
            DAY_OF_MONTH,
            MONTH,
            DAY_OF_WEEK,
            COMMAND
    );
}
