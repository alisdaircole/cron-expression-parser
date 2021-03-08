package model;

import java.util.List;

public class Cron {

    private final List<Integer> cronResults;
    private final TimeField timeField;

    public Cron(List<Integer> cronResults, TimeField timeField) {
        this.cronResults = cronResults;
        this.timeField = timeField;
    }

    public List<Integer> getCronResults() {
        return cronResults;
    }
}
