package ru.mydesignstudio.search.sql.app.rest.model.request;

import ru.mydesignstudio.search.sql.app.service.SearchType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Model class for type's property request.
 */
public class TypePropertiesRequest {
    /**
     * Requested type.
     */
    @NotNull
    @Size(min = 1)
    private String requestedType;
    /**
     * Search types.
     */
    @NotNull
    @Size(min = 1)
    private Collection<SearchType> searchTypes = new ArrayList<>();

    public String getRequestedType() {
        return requestedType;
    }

    public void setRequestedType(String requestedType) {
        this.requestedType = requestedType;
    }

    public Collection<SearchType> getSearchTypes() {
        return searchTypes;
    }

    public void setSearchTypes(Collection<SearchType> searchTypes) {
        this.searchTypes = searchTypes;
    }
}
