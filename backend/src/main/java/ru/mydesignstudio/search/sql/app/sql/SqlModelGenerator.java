package ru.mydesignstudio.search.sql.app.sql;

import ru.mydesignstudio.search.sql.app.model.ModelDefinition;

import java.io.File;

public interface SqlModelGenerator {
    /**
     * Read model definition from PMA xml file.
     *
     * @param xmlSqlSource path to xml PMA output
     * @return parsed model
     */
    ModelDefinition generate(File xmlSqlSource);
}
