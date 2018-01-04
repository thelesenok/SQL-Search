package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.response.SearchResponse;
import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSet;
import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSetRow;
import ru.mydesignstudio.search.sql.app.service.search.weight.calculator.WeightCalculator;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class SearchResponseTransformer implements Transformer<SqlResultSet, SearchResponse> {
    private static final String WEIGHT_COLUMN = "WEIGHT";

    @Autowired
    private WeightCalculator weightCalculator;

    @Override
    public SearchResponse transform(SqlResultSet resultSet) {
        final Collection<Collection<String>> rowCollection = new ArrayList<>();
        final Collection<String> heading = new ArrayList<>(resultSet.getColumnNames());
        heading.add(WEIGHT_COLUMN);
        rowCollection.add(heading);
        for (SqlResultSetRow sqlRow : resultSet.getRows()) {
            final Collection<String> row = new ArrayList<>();
            for (String columnName : sqlRow.getColumnNames()) {
                row.add(sqlRow.getValue(columnName));
            }
            row.add(String.format(
                    "%.3f%n",
                    weightCalculator.calculate(sqlRow)
            ));
            rowCollection.add(row);
        }
        final SearchResponse response = new SearchResponse();
        response.setRows(rowCollection);
        return response;
    }
}
