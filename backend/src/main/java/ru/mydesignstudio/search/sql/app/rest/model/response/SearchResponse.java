package ru.mydesignstudio.search.sql.app.rest.model.response;

import java.util.ArrayList;
import java.util.Collection;

public class SearchResponse {
    private Collection<Collection<String>> rows = new ArrayList<>();

    public Collection<Collection<String>> getRows() {
        return rows;
    }

    public void setRows(Collection<Collection<String>> rows) {
        this.rows = rows;
    }
}
