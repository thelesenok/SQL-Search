package ru.mydesignstudio.search.sql.app.service.search;

import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.service.search.result.set.SqlResultSet;

import java.util.Collection;
import java.util.Map;

public interface SearchService {
    SqlResultSet search(String query);

    Map<Integer, String> findLookupValues(TypeDefinition typeDefinition);
}
