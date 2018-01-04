package ru.mydesignstudio.search.sql.app.service.model.definition.reader;

import ru.mydesignstudio.search.sql.app.model.ModelDefinition;

import java.io.File;
import java.io.InputStream;

/**
 * Reads model definition
 */
public interface ModelDefinitionReader {
    /**
     * Read model definition from file.
     *
     * @param file model definition file
     * @return model definition
     */
    ModelDefinition read(InputStream inputStream);
}
