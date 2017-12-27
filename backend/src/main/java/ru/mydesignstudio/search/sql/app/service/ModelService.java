package ru.mydesignstudio.search.sql.app.service;

import ru.mydesignstudio.search.sql.app.model.TypeDefinition;

import java.util.Collection;

public interface ModelService {
    /**
     * Find all declared types.
     *
     * @return
     */
    Collection<TypeDefinition> findAllTypes();
}
