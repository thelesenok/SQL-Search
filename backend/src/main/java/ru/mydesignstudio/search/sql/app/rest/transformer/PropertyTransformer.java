package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.rest.model.PropertyResponse;

@Component
public class PropertyTransformer implements Transformer<PropertyDefinition, PropertyResponse> {
    @Autowired
    private PropertyValueTypeTransformer valueTypeTransformer;

    @Override
    public PropertyResponse transform(PropertyDefinition property) {
        final PropertyResponse response = new PropertyResponse();
        response.setValue(property.getPropertyColumn());
        response.setLabel(property.getPropertyName());
        response.setValueType(valueTypeTransformer.transform(property.getPropertyType()));
        return response;
    }
}
