package ru.mydesignstudio.search.sql.app.service;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.Collection;
import java.util.HashSet;

@Component
public class PropertyDefinitionFinder {

    public Collection<PropertyDefinition> findDefinitions(
            final TypeDefinition typeDefinition,
            final Collection<SearchType> searchTypes
            ) {

        Validations.assertNotNull(typeDefinition, "Type definition wasn't provided");
        Validations.assertNotNull(searchTypes, "Search types wasn't provided");
        Validations.assertTrue(!searchTypes.isEmpty(), "Search types are empty");

        final Collection<PropertyDefinition> result = new HashSet<>();
        // find all numeric and string properties for attributive fields
        final Collection<PropertyDefinition> properties = typeDefinition.getProperties();
        if (searchTypes.contains(SearchType.ATTRIBUTIVE)) {
            for (PropertyDefinition property : properties) {
                if (PropertyType.STRING.equals(property.getPropertyType()) ||
                        PropertyType.NUMBER.equals(property.getPropertyType())) {

                    result.add(property);
                }
            }
        }
        // add all reference fields is relational search is enabled
        if (searchTypes.contains(SearchType.RELATIONAL)) {
            for (PropertyDefinition property : properties) {
                if (PropertyType.REFERENCE.equals(property.getPropertyType())) {
                    result.add(property);
                }
            }
        }
        return result;
    }
}
