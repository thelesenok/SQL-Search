package ru.mydesignstudio.search.sql.app.service.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class JoinsBuilder {
    @Autowired
    private ModelService modelService;

    public String buildJoins(SearchRequest request) {
        Validations.assertNotNull(request, "Search request wasn't provided");

        final Collection<String> parts = new ArrayList<>();
        for (SearchRequest.SearchAttribute attribute : request.getAttributes()) {
            if (isNeedJoin(request, attribute)) {
                parts.add(buildAttribute(request, attribute));
            }
        }
        return StringUtils.join(parts, " ");
    }

    private String buildAttribute(SearchRequest request, SearchRequest.SearchAttribute attribute) {
        final TypeDefinition selectType = modelService.findType(request.getSelectFrom());
        final TypeDefinition attributeType = modelService.findType(attribute.getSelectClass());
        final PropertyDefinition attributePK = modelService.findPrimaryKeyProperty(attributeType);
        final PropertyDefinition relationProperty = modelService.findRelationProperty(selectType, attributeType);

        final StringBuilder builder = new StringBuilder();
        builder.append(" INNER JOIN ");
        builder.append(attributeType.getTableName());
        builder.append(" ON ");
        builder.append(selectType.getTableName());
        builder.append(".");
        builder.append(attributePK.getPropertyColumn());
        builder.append(" = ");
        builder.append(selectType.getTableName());
        builder.append(".");
        builder.append(relationProperty.getPropertyColumn());

        return builder.toString();
    }

    private boolean isNeedJoin(SearchRequest request, SearchRequest.SearchAttribute attribute) {
        final TypeDefinition selectType = modelService.findType(request.getSelectFrom());
        final TypeDefinition attributeType = modelService.findType(attribute.getSelectClass());

        return !selectType.equals(attributeType);
    }
}
