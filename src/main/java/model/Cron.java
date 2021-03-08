package model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static util.StringFormatter.getStringWithWhitespacePadding;

public class Cron {

    private final List<Integer> cronResults;
    private final TimeField timeField;

    public Cron(List<Integer> cronResults, TimeField timeField) {
        this.cronResults = cronResults;
        this.timeField = timeField;
    }

    @Override
    public String toString() {
        String cronResultValues = cronResults.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        return cronResultValues.isEmpty() ? timeField.toString() :
                getStringWithWhitespacePadding(timeField.toString(), cronResultValues);
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
