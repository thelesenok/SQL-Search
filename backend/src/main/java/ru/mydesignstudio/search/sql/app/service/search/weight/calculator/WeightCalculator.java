package ru.mydesignstudio.search.sql.app.service.search.weight.calculator;

import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSetRow;

public interface WeightCalculator {
    double calculate(SqlResultSetRow row);
}
