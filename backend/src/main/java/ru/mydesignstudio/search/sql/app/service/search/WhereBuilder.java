package ru.mydesignstudio.search.sql.app.service.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class WhereBuilder {
    @Autowired
    private ModelService modelService;
    @Autowired
    private WhereOperationBuilder operationBuilder;
    @Autowired
    private WhereValueBuilder valueBuilder;

    public String buildWhere(SearchRequest request) {
        Validations.assertNotNull(request, "Search request wasn't provided");

        final Collection<String> parts = new ArrayList<>();
        for (SearchRequest.SearchAttribute attribute : request.getAttributes()) {
            parts.add(buildAttribute(request, attribute));
        }
        return StringUtils.join(
                parts,
                " " + request.getJoinType().getValue().toUpperCase() + " "
        );
    }

    private String buildAttribute(SearchRequest request, SearchRequest.SearchAttribute attribute) {
        final TypeDefinition attributeType = modelService.findType(attribute.getSelectClass());

        final StringBuilder builder = new StringBuilder();
        builder.append(attributeType.getTableName());
        builder.append(".");
        builder.append(attribute.getProperty());
        builder.append(operationBuilder.buildOperation(request, attribute));
        builder.append(valueBuilder.buildWhereValue(request, attribute));

        return builder.toString();
    }
}
