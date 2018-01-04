package ru.mydesignstudio.search.sql.app.service.search.proximity.calculator;

import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSetRow;

public interface ProximityCalculator {
    double calculate(SqlResultSetRow row, SearchRequest.SearchAttribute attribute);
}
