package ru.mydesignstudio.search.sql.app.service.search;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.utils.Validations;

@Component
public class WhereValueBuilder {
    public String buildWhereValue(final SearchRequest request, final SearchRequest.SearchAttribute attribute) {
        Validations.assertNotNull(request, "Search request wasn't provided");
        Validations.assertNotNull(attribute, "Search attribute wasn't provided");

        switch (attribute.getValueType()) {
            case NUMBER: return attribute.getValue();
            case STRING:
                if (attribute.getLogicalOperation().equals(LogicalOperation.LIKE)) {
                    return "\"%" + attribute.getValue() + "%\"";
                } else if (attribute.getLogicalOperation().equals(LogicalOperation.EQUALS)) {
                    return "\"" + attribute.getValue() + "\"";
                }
        }
        throw new RuntimeException(String.format(
                "Operation %s is not supported",
                attribute.getValueType()
        ));
    }
}
