package parser;

import model.CronExpression;

public interface ParseInput {

    CronExpression getCronExpression(String input);
}
