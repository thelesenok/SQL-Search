package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.rest.model.PropertyValueType;

@Component
public class PropertyValueTypeTransformer implements Transformer<PropertyType, PropertyValueType> {
    @Override
    public PropertyValueType transform(PropertyType source) {
        if (PropertyType.STRING.equals(source)) {
            return PropertyValueType.STRING;
        }
        if (PropertyType.NUMBER.equals(source)) {
            return PropertyValueType.NUMBER;
        }
        if (PropertyType.REFERENCE.equals(source)) {
            return PropertyValueType.REFERENCE;
        }
        throw new RuntimeException(String.format(
                "Unsupported value type %s",
                source
        ));
    }
}
