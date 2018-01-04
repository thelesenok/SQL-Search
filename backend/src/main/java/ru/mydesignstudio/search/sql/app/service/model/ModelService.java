package ru.mydesignstudio.search.sql.app.service.model;

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

    /**
     * Finds property by name.
     *
     * @param typeDefinition type definition
     * @param propertyName property name
     * @return property definition
     */
    PropertyDefinition findProperty(TypeDefinition typeDefinition, String propertyName);

    /**
     * Find display property for provided type.
     *
     * @param typeDefinition type definition
     * @return display property
     */
    PropertyDefinition findDisplayProperty(TypeDefinition typeDefinition);

    /**
     * Find type primary key property.
     *
     * @param typeDefinition type definition
     * @return primary key property
     */
    PropertyDefinition findPrimaryKeyProperty(TypeDefinition typeDefinition);

    /**
     * Find property that connects to types.
     *
     * @param sourceType from type
     * @param targetType target type
     * @return connection property
     */
    PropertyDefinition findRelationProperty(TypeDefinition sourceType, TypeDefinition targetType);
}
