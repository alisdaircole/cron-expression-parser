package model;

import java.util.Objects;

import static util.StringFormatter.getStringWithWhitespacePadding;

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

    @Override
    public String toString() {
        return minute.toString() + "\n" +
                hour.toString() + "\n" +
                dayOfMonth.toString() + "\n" +
                month.toString() + "\n" +
                dayOfWeek.toString() + "\n" +
                getStringWithWhitespacePadding("command", command);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CronExpression that = (CronExpression) o;
        return minute.equals(that.minute) && hour.equals(that.hour) && dayOfMonth.equals(that.dayOfMonth)
                && month.equals(that.month) && dayOfWeek.equals(that.dayOfWeek) && command.equals(that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minute, hour, dayOfMonth, month, dayOfWeek, command);
    }
}
