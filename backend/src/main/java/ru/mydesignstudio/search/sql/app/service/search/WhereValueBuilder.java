package ru.mydesignstudio.search.sql.app.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.utils.Validations;

@Component
public class WhereValueBuilder {
    @Autowired
    private ModelService modelService;

    public String buildWhereValue(final SearchRequest request, final SearchRequest.SearchAttribute attribute) {
        Validations.assertNotNull(request, "Search request wasn't provided");
        Validations.assertNotNull(attribute, "Search attribute wasn't provided");

        final TypeDefinition typeDefinition = modelService.findType(attribute.getSelectClass());
        final PropertyDefinition propertyDefinition =
                modelService.findProperty(typeDefinition, attribute.getProperty());

        switch (attribute.getValueType()) {
            case SELECT:
                return attribute.getValue();
            case TEXT_FIELD:
                switch (attribute.getLogicalOperation()) {
                    case LIKE:
                    case CONTAINS:
                        return "'%" + attribute.getValue() + "%'";
                    case STARTS_WITH:
                        return "'" + attribute.getValue() + "%'";
                    case ENDS_WITH:
                        return "'%" + attribute.getValue() + "'";
                    case MORE_OR_EQUALS:
                    case MORE_THAN:
                    case LESS_OR_EQUALS:
                    case LESS_THAN:
                        return attribute.getValue();
                    case EQUALS:
                        switch (propertyDefinition.getPropertyType()) {
                            case NUMBER:
                                return attribute.getValue();
                            case STRING:
                                return "'" + attribute.getValue() + "'";
                            default:
                                throw new RuntimeException(String.format(
                                        "Property type %s not supported",
                                        propertyDefinition.getPropertyType()
                                ));
                        }
                    default:
                        throw new RuntimeException(String.format(
                                "Operation %s not supported",
                                attribute.getLogicalOperation()
                        ));
                }
        }
        throw new RuntimeException(String.format(
                "Operation %s is not supported",
                attribute.getValueType()
        ));
    }
}
