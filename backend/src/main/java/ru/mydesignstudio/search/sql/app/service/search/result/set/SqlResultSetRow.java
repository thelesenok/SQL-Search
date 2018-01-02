package ru.mydesignstudio.search.sql.app.service.search.result.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SqlResultSetRow {
    private final Map<String, String> cells = new HashMap<>();
    private final Collection<Double> weights = new ArrayList<>();

    public Collection<String> getColumnNames() {
        return cells.keySet();
    }

    public void addValue(final String columnName, final String value) {
        cells.put(columnName, value);
    }

    public String getValue(final String columnName) {
        if (!cells.containsKey(columnName)) {
            return "";
        }
        return cells.get(columnName);
    }

    public void addWeight(double weight) {
        weights.add(weight);
    }

    public Collection<Double> getWeights() {
        return weights;
    }
}
