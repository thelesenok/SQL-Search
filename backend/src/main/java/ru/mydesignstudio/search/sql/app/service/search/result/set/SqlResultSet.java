package ru.mydesignstudio.search.sql.app.service.search.result.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SqlResultSet {
    private final Collection<SqlResultSetRow> rows = new ArrayList<>();
    private final Map<Integer, String> columnNames = new HashMap<>();

    public void addRow(final SqlResultSetRow row) {
        for (String columnName : row.getColumnNames()) {
            if (!columnNames.containsValue(columnName)) {
                columnNames.put(
                        columnNames.size(),
                        columnName
                );
            }
        }
        rows.add(row);
    }

    public Collection<String> getColumnNames() {
        return columnNames.values();
    }

    public Collection<SqlResultSetRow> getRows() {
        return rows;
    }
}
