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
            case LIKE: return " LIKE ";
        }
        throw new RuntimeException(String.format(
                "Operation %s is not supported",
                attribute.getLogicalOperation()
        ));
    }
}
