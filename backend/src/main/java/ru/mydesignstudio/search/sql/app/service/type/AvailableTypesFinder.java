package ru.mydesignstudio.search.sql.app.service.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.service.SearchType;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.Collection;
import java.util.LinkedHashSet;

@Component
public class AvailableTypesFinder {
    @Autowired
    private ModelService modelService;

    public Collection<TypeDefinition> findTypes(
            final TypeDefinition requestType,
            final Collection<SearchType> searchTypes
    ) {

        Validations.assertNotNull(requestType, "Request type wasn't provided");
        Validations.assertNotNull(searchTypes, "Search types wasn't provided");
        Validations.assertTrue(!searchTypes.isEmpty(), "Search types are empty");

        final Collection<TypeDefinition> availableTypes = new LinkedHashSet<>();
        // selected type are always available
        availableTypes.add(requestType);
        // if relational search is available it's necessary to add related types
        if (searchTypes.contains(SearchType.RELATIONAL)) {
            // until now, we will add only direct relations, not backward
            for (PropertyDefinition property : requestType.getProperties()) {
                if (PropertyType.REFERENCE.equals(property.getPropertyType())) {
                    final TypeDefinition referencedType = modelService.findType(property.getPropertyType().getType());
                    availableTypes.add(referencedType);
                }
            }
        }
        return availableTypes;
    }
}
