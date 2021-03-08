package parser;

import model.CronExpression;
import model.TimeField;

import static parser.ParseCronString.getCron;
import static validation.Validation.validateInputSize;

public class ParseInputImpl implements ParseInput {

    @Override
    public CronExpression getCronExpression(String input) {
        String[] cronValues = input.split(" ");
        validateInputSize(cronValues);

        return new CronExpression(
                getCron(cronValues[0], TimeField.MINUTE),
                getCron(cronValues[1], TimeField.HOUR),
                getCron(cronValues[2], TimeField.DAY_OF_MONTH),
                getCron(cronValues[3], TimeField.MONTH),
                getCron(cronValues[4], TimeField.DAY_OF_WEEK),
                cronValues[5]
        );
    }
}
