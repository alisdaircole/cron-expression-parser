package model;

public class CronExpression {

    private final Cron minute;
    private final Cron hour;
    private final Cron dayOfMonth;
    private final Cron month;
    private final Cron dayOfWeek;
    private final String command;

    public CronExpression(Cron minute, Cron hour, Cron dayOfMonth, Cron month, Cron dayOfWeek,
                          String command) {
        this.minute = minute;
        this.hour = hour;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.command = command;
    }
}
