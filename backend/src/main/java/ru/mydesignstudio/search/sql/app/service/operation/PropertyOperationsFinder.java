package ru.mydesignstudio.search.sql.app.service.operation;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.service.SearchType;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

@Component
public class PropertyOperationsFinder {
    // todo, this code should be moved to configuration
    private Map<SearchType, Map<PropertyType, Collection<LogicalOperation>>> operations = new HashMap<>();

    @PostConstruct
    public void init() {
        final Map<PropertyType, Collection<LogicalOperation>> attributive = new HashMap<>();
        attributive.put(PropertyType.STRING, Arrays.asList(
                LogicalOperation.CONTAINS,
                LogicalOperation.EQUALS,
                LogicalOperation.STARTS_WITH,
                LogicalOperation.ENDS_WITH
        ));
        attributive.put(PropertyType.NUMBER, Arrays.asList(
                LogicalOperation.MORE_THAN,
                LogicalOperation.MORE_OR_EQUALS,
                LogicalOperation.EQUALS,
                LogicalOperation.LESS_THAN,
                LogicalOperation.LESS_OR_EQUALS
        ));
        attributive.put(PropertyType.REFERENCE, Collections.emptyList());

        final Map<PropertyType, Collection<LogicalOperation>> relational = new HashMap<>();
        relational.put(PropertyType.STRING, Collections.emptyList());
        relational.put(PropertyType.NUMBER, Collections.emptyList());
        relational.put(PropertyType.REFERENCE, Arrays.asList(
                LogicalOperation.EQUALS
        ));

        final Map<PropertyType, Collection<LogicalOperation>> fuzzyLike = new HashMap<>();
        fuzzyLike.put(PropertyType.STRING, Arrays.asList(
                LogicalOperation.FUZZY_LIKE
        ));
        fuzzyLike.put(PropertyType.NUMBER, Collections.emptyList());
        fuzzyLike.put(PropertyType.REFERENCE, Collections.emptyList());

        operations.put(SearchType.ATTRIBUTIVE, attributive);
        operations.put(SearchType.RELATIONAL, relational);
        operations.put(SearchType.FUZZY, fuzzyLike);
    }

    /**
     * Find logical operations available for designated search types.
     *
     * @param propertyDefinition property to search operations for
     * @param searchTypes search types
     * @return
     */
    public Collection<LogicalOperation> findOperations(
            final PropertyDefinition propertyDefinition,
            final Collection<SearchType> searchTypes) {

        Validations.assertNotNull(propertyDefinition, "Property definition not provided");
        Validations.assertNotNull(searchTypes, "Search types not provided");
        Validations.assertTrue(!searchTypes.isEmpty(), "Search types not defined");

        final Collection<LogicalOperation> result = new LinkedHashSet<>();
        for (SearchType searchType : searchTypes) {
            final Map<PropertyType, Collection<LogicalOperation>> byType = operations.get(searchType);
            Validations.assertNotNull(byType, String.format(
                    "There is no configuration for search type %s",
                    searchType
            ));
            final Collection<LogicalOperation> operations = byType.get(propertyDefinition.getPropertyType());
            Validations.assertNotNull(operations, String.format(
                    "There is no configuration for property type %s",
                    propertyDefinition.getPropertyType()
            ));
            result.addAll(operations);
        }
        return result;
    }
}
