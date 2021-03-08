package util;

public final class StringFormatter {

    private static final int COLUMN_SIZE = 14;

    private StringFormatter() {
    }

    public static String getStringWithWhitespacePadding(String fieldName, String fieldValue) {
        return String.format("%-" + COLUMN_SIZE + "s%s", fieldName, fieldValue);
    }
}
