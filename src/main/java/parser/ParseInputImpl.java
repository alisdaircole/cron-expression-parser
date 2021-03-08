package parser;

import model.CronExpression;

import static validation.Validation.validateInputSize;

public class ParseInputImpl implements ParseInput {

    @Override
    public CronExpression getCronExpression(String input) {
        String[] cronValues = input.split(" ");
        validateInputSize(cronValues);

        return new CronExpression(
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
