package ru.mydesignstudio.search.sql.app.service.search.weight.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.service.search.proximity.calculator.ProximityCalculator;
import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSet;
import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSetRow;
import ru.mydesignstudio.search.sql.app.utils.Validations;

@Component
public class RowWeightCalculator {
    @Autowired
    @Qualifier("defaultCalculator")
    private ProximityCalculator defaultCalculator;
    @Autowired
    @Qualifier("likeCalculator")
    private ProximityCalculator likeCalculator;

    public SqlResultSet weightResultSet(final SqlResultSet sourceSet,
                                        final SearchRequest searchRequest) {

        Validations.assertNotNull(searchRequest, "Search request wasn't provided");
        Validations.assertNotNull(sourceSet, "Source set wasn't provided");

        final SqlResultSet weightedSet = new SqlResultSet();
        for (SqlResultSetRow sourceRow : sourceSet.getRows()) {
            weightedSet.addRow(weightRow(sourceRow, searchRequest));
        }
        return weightedSet;
    }

    private SqlResultSetRow weightRow(final SqlResultSetRow sourceRow,
                                      final SearchRequest searchRequest) {
        final SqlResultSetRow weightedRow = new SqlResultSetRow();

        // copy old values
        for (String columnName : sourceRow.getColumnNames()) {
            weightedRow.addValue(columnName, sourceRow.getValue(columnName));
        }
        // weight them
        for (SearchRequest.SearchAttribute attribute : searchRequest.getAttributes()) {
            weightedRow.addWeight(calculateWeight(weightedRow, attribute));
        }

        return weightedRow;
    }

    private double calculateWeight(SqlResultSetRow weightedRow, SearchRequest.SearchAttribute attribute) {
        switch (attribute.getLogicalOperation()) {
            case MORE_OR_EQUALS:
            case MORE_THAN:
            case LESS_OR_EQUALS:
            case LESS_THAN:
            case EQUALS:
                return defaultCalculator.calculate(weightedRow, attribute);
            case LIKE:
            case FUZZY_LIKE:
            case CONTAINS:
                return likeCalculator.calculate(weightedRow, attribute);
            default:
                throw new RuntimeException(String.format(
                        "Unsupported logical operation %s",
                        attribute.getLogicalOperation()
                ));
        }
    }
}
