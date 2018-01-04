package ru.mydesignstudio.search.sql.app.service.search.proximity.calculator;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSetRow;
import ru.mydesignstudio.search.sql.app.utils.Validations;

@Component("defaultCalculator")
public class DefaultProximityCalculator implements ProximityCalculator {
    @Override
    public double calculate(SqlResultSetRow row, SearchRequest.SearchAttribute attribute) {
        Validations.assertNotNull(row, "Row wasn't provided");
        Validations.assertNotNull(attribute, "Attribute wasn't provided");

        return 1;
    }
}
