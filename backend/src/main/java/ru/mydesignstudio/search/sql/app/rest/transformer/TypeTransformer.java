package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;

@Component
public class TypeTransformer implements Transformer<TypeDefinition, String> {
    @Override
    public String transform(TypeDefinition source) {
        return source.getTypeName();
    }
}
