package model;

import java.util.List;
import java.util.Objects;

public class Cron {

    private final List<Integer> cronResults;
    private final TimeField timeField;

    public Cron(List<Integer> cronResults, TimeField timeField) {
        this.cronResults = cronResults;
        this.timeField = timeField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cron cron = (Cron) o;
        return cronResults.equals(cron.cronResults) && timeField == cron.timeField;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cronResults, timeField);
    }
}
