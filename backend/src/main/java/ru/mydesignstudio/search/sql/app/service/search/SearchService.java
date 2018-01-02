package ru.mydesignstudio.search.sql.app.service.search;

import ru.mydesignstudio.search.sql.app.model.TypeDefinition;

import java.util.Collection;
import java.util.Map;

public interface SearchService {
    Collection<Collection<String>> search(String query);

    Map<Integer, String> findLookupValues(TypeDefinition typeDefinition);
}
