package ru.mydesignstudio.search.sql.app.service.search;

import java.util.Collection;

public interface SearchService {
    Collection<Collection<String>> search(String query);
}
