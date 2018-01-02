package ru.mydesignstudio.search.sql.app.service.search;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.utils.Validations;

@Component
public class WhereOperationBuilder {
    public String buildOperation(final SearchRequest request, final SearchRequest.SearchAttribute attribute) {
        Validations.assertNotNull(request, "Search request wasn't provided");
        Validations.assertNotNull(attribute, "Search attribute wasn't provided");

        switch (attribute.getLogicalOperation()) {
            case EQUALS: return " = ";
            case LIKE:
            case ENDS_WITH:
            case STARTS_WITH:
            case CONTAINS:
                return " LIKE ";
            case MORE_OR_EQUALS:
                return " >= ";
            case MORE_THAN:
                return " > ";
            case LESS_OR_EQUALS:
                return " <= ";
            case LESS_THAN:
                return " < ";
        }
        throw new RuntimeException(String.format(
                "Operation %s is not supported",
                attribute.getLogicalOperation()
        ));
    }
}
