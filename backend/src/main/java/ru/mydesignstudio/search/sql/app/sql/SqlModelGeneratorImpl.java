package ru.mydesignstudio.search.sql.app.sql;

import net.sf.jsqlparser.statement.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class SqlModelGeneratorImpl implements SqlModelGenerator {
    @Autowired
    private SqlQueryLoader queryLoader;
    @Autowired
    private SqlStatementBuilder statementBuilder;

    @Override
    public ModelDefinition generate(File xmlSqlSource) {
        Validations.assertNotNull(xmlSqlSource, "Source file wasn't provided");

        final Collection<String> queries = queryLoader.loadQueries(xmlSqlSource);
        final Collection<Statement> statements = new ArrayList<>();
        for (String query : queries) {
            statements.add(statementBuilder.buildStatement(query));
        }
        return null;
    }
}
