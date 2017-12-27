package ru.mydesignstudio.search.sql.app.service;

import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;

import java.util.Collection;

public interface ModelService {
    /**
     * Finds all declared types.
     *
     * @return
     */
    Collection<TypeDefinition> findAllTypes();

    /**
     * Finds type definition by it's name.
     *
     * @param typeName type name.
     * @return type definition
     */
    TypeDefinition findType(String typeName);
}
