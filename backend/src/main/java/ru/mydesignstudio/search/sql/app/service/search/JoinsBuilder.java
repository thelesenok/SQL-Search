package ru.mydesignstudio.search.sql.app.service.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
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
            } else if (isReferenceAttribute(attribute)) {
                parts.add(buildReference(attribute));
            }
        }
        return StringUtils.join(parts, " ");
    }

    private String buildReference(SearchRequest.SearchAttribute attribute) {
        final TypeDefinition typeDefinition = modelService.findType(attribute.getSelectClass());
        final PropertyDefinition propertyDefinition =
                modelService.findProperty(typeDefinition, attribute.getProperty());
        final TypeDefinition referenceType =
                modelService.findType(propertyDefinition.getTypeReference().getReferenceType());
        final PropertyDefinition referencePK = modelService.findPrimaryKeyProperty(referenceType);

        final StringBuilder builder = new StringBuilder();
        builder.append(" INNER JOIN ")
                .append(referenceType.getTableName())
                .append(" ON ")
                .append(typeDefinition.getTableName())
                .append(".")
                .append(propertyDefinition.getPropertyColumn())
                .append(" = ")
                .append(referenceType.getTableName())
                .append(".")
                .append(referencePK.getPropertyColumn());
        return builder.toString();
    }

    private boolean isReferenceAttribute(SearchRequest.SearchAttribute attribute) {
        final TypeDefinition typeDefinition = modelService.findType(attribute.getSelectClass());
        final PropertyDefinition propertyDefinition =
                modelService.findProperty(typeDefinition, attribute.getProperty());

        return PropertyType.REFERENCE.equals(propertyDefinition.getPropertyType());
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
