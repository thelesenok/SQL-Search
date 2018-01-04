package ru.mydesignstudio.search.sql.app.rest.model.request;

import ru.mydesignstudio.search.sql.app.service.SearchType;

import java.util.ArrayList;
import java.util.Collection;

public class AvailableTypesRequest {
    private String selectType;
    private Collection<SearchType> searchTypes = new ArrayList<>();

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public Collection<SearchType> getSearchTypes() {
        return searchTypes;
    }

    public void setSearchTypes(Collection<SearchType> searchTypes) {
        this.searchTypes = searchTypes;
    }
}
