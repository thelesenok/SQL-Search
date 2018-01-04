package ru.mydesignstudio.search.sql.app.sql;

import net.sf.jsqlparser.statement.Statement;

public interface SqlService {
    Statement parseSql(String sql);
}
