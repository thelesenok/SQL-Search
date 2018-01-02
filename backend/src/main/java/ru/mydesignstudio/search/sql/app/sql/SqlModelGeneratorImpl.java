package ru.mydesignstudio.search.sql.app.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.sql.extractor.PropertiesExtractor;
import ru.mydesignstudio.search.sql.app.sql.extractor.TableExtractor;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class SqlModelGeneratorImpl implements SqlModelGenerator {
    @Autowired
    private SqlQueryLoader queryLoader;
    @Autowired
    private TableExtractor tableExtractor;
    @Autowired
    private PropertiesExtractor propertiesExtractor;

    @Override
    public ModelDefinition generate(File xmlSqlSource) {
        Validations.assertNotNull(xmlSqlSource, "Source file wasn't provided");

        final Collection<String> queries = queryLoader.loadQueries(xmlSqlSource);
        final ModelDefinition definition = new ModelDefinition();
        definition.setTypes(generateTypes(queries));
        return definition;
    }

    private Collection<TypeDefinition> generateTypes(Collection<String> queries) {
        final Collection<TypeDefinition> definitions = new ArrayList<>();
        for (String query : queries) {
            definitions.add(generateType(query));
        }
        return definitions;
    }

    private TypeDefinition generateType(String query) {
        final TypeDefinition typeDefinition = new TypeDefinition();
        typeDefinition.setTableName(tableExtractor.extract(query));
        typeDefinition.setTypeName(tableExtractor.extract(query));
        typeDefinition.setProperties(propertiesExtractor.extract(query));
        return typeDefinition;
    }
}
