package ru.mydesignstudio.search.sql.app.service.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.utils.Validations;

@Component
public class QueryBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryBuilder.class);

    @Autowired
    private SelectBuilder selectBuilder;
    @Autowired
    private FromBuilder fromBuilder;
    @Autowired
    private JoinsBuilder joinsBuilder;
    @Autowired
    private WhereBuilder whereBuilder;

    public String buildQuery(SearchRequest request) {
        Validations.assertNotNull(request, "Search request wasn't provided");

        final StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        builder.append(selectBuilder.buildSelect(request));
        builder.append(" FROM ");
        builder.append(fromBuilder.buildFrom(request));
        builder.append(" ");
        builder.append(joinsBuilder.buildJoins(request));
        builder.append(" WHERE ");
        builder.append(whereBuilder.buildWhere(request));
        final String query = builder.toString();

        LOGGER.debug(query);

        return query;
    }
}
