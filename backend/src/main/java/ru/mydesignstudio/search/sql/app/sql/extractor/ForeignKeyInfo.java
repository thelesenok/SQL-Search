package ru.mydesignstudio.search.sql.app.sql.extractor;

public class ForeignKeyInfo {
    private final String keyColumn;
    private final String keyTable;

    public ForeignKeyInfo(String keyColumn, String keyTable) {
        this.keyColumn = keyColumn;
        this.keyTable = keyTable;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public String getKeyTable() {
        return keyTable;
    }
}
