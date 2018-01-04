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

import java.util.Collection;
import java.util.HashSet;

@Component
public class SelectBuilder {
    @Autowired
    private ModelService modelService;

    public String buildSelect(SearchRequest request) {
        Validations.assertNotNull(request, "Search request wasn't provided");

        final Collection<String> parts = new HashSet<>();
        parts.add(buildPrimaryKey(request));
        for (SearchRequest.SearchAttribute attribute : request.getAttributes()) {
            parts.add(buildAttribute(attribute));
        }
        return StringUtils.join(parts, ", ");
    }

    private String buildPrimaryKey(SearchRequest request) {
        final StringBuilder builder = new StringBuilder();

        final TypeDefinition selectType = modelService.findType(request.getSelectFrom());
        final PropertyDefinition primaryKeyProperty = modelService.findPrimaryKeyProperty(selectType);

        builder.append(selectType.getTableName())
                .append(".")
                .append(primaryKeyProperty.getPropertyColumn())
                .append(" AS ID");

        return builder.toString();
    }

    private String buildAttribute(SearchRequest.SearchAttribute attribute) {
        final StringBuilder builder = new StringBuilder();

        final TypeDefinition attributeType = modelService.findType(attribute.getSelectClass());
        final PropertyDefinition attributeProperty = modelService.findProperty(attributeType, attribute.getProperty());

        if (PropertyType.REFERENCE.equals(attributeProperty.getPropertyType())) {
            final TypeDefinition referenceType = modelService.findType(
                    attributeProperty.getTypeReference().getReferenceType()
            );
            final PropertyDefinition displayProperty = modelService.findDisplayProperty(referenceType);
            builder.append(referenceType.getTableName());
            builder.append(".");
            builder.append(displayProperty.getPropertyColumn());
        } else {
            builder.append(attributeType.getTableName());
            builder.append(".");
            builder.append(attributeProperty.getPropertyColumn());
        }
        
        return builder.toString();
    }


}
