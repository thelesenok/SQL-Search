package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.rest.model.response.PropertyResponse;

@Component
public class PropertyTransformer implements Transformer<PropertyDefinition, PropertyResponse> {
    @Override
    public PropertyResponse transform(PropertyDefinition property) {
        final PropertyResponse response = new PropertyResponse();
        response.setValue(property.getPropertyColumn());
        response.setLabel(property.getPropertyName());
        response.setValueType(property.getPropertyType());
        return response;
    }
}
