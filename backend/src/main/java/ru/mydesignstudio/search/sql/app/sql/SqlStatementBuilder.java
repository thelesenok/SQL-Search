package ru.mydesignstudio.search.sql.app.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.Collection;

@Component
public class SqlStatementBuilder {
    public Statement buildStatement(final String sqlString) {
        Validations.assertTrue(StringUtils.isNoneBlank(sqlString), "SQL string wasn't provided");

        try {
            return CCJSqlParserUtil.parse(sqlString);
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }
}
