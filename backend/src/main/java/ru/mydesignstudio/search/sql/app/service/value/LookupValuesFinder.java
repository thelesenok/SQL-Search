package ru.mydesignstudio.search.sql.app.service.value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.service.search.SearchService;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.Map;

@Component
public class LookupValuesFinder {
    @Autowired
    private SearchService searchService;
    @Autowired
    private ModelService modelService;

    public Map<Integer, String> findLookupValues(PropertyDefinition propertyDefinition) {
        Validations.assertNotNull(propertyDefinition, "Property definition wasn't provided");

        final TypeDefinition referencedType =
                modelService.findType(propertyDefinition.getTypeReference().getReferenceType());

        return searchService.findLookupValues(referencedType);
    }
}
