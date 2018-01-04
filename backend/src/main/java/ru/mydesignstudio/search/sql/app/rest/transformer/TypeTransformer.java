package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.rest.model.response.TypeResponse;

@Component
public class TypeTransformer implements Transformer<TypeDefinition, TypeResponse> {
    @Override
    public TypeResponse transform(TypeDefinition source) {
        final TypeResponse response = new TypeResponse();
        response.setLabel(source.getTypeName());
        response.setValue(source.getTableName());
        return response;
    }
}
