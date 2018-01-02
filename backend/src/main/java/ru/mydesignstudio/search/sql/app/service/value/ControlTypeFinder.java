package ru.mydesignstudio.search.sql.app.service.value;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.service.SearchType;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;

import java.util.Collection;

@Component
public class ControlTypeFinder {
    /**
     * Find proper control type for provided parameters.
     *
     * @param typeDefinition
     * @param propertyDefinition
     * @param logicalOperation
     * @param searchTypes
     * @return
     */
    public ControlType findControlType(
            final TypeDefinition typeDefinition,
            final PropertyDefinition propertyDefinition,
            final LogicalOperation logicalOperation,
            final Collection<SearchType> searchTypes) {

        if (isLookupFieldAndSearch(searchTypes, propertyDefinition)) {
            return ControlType.SELECT;
        }
        return ControlType.TEXT_FIELD;
    }

    private boolean isLookupFieldAndSearch(Collection<SearchType> searchTypes, PropertyDefinition propertyDefinition) {
        if (!searchTypes.contains(SearchType.RELATIONAL)) {
            return false;
        }
        if (!PropertyType.REFERENCE.equals(propertyDefinition.getPropertyType())) {
            return false;
        }
        return true;
    }
}
