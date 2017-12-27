package ru.mydesignstudio.search.sql.app.service.value;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
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

        // actually, it doesn't depends on type right now, but let it be
        return ControlType.TEXT_FIELD;
    }
}
