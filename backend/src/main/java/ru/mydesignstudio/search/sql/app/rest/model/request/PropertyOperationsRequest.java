package ru.mydesignstudio.search.sql.app.rest.model.request;

import ru.mydesignstudio.search.sql.app.service.SearchType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Model class for operations request.
 */
public class PropertyOperationsRequest {
    @NotNull
    @Size(min = 1)
    private String requestedType;
    @NotNull
    @Size(min = 1)
    private String requestedProp;
    @NotNull
    @Size(min = 1)
    private Collection<SearchType> searchTypes = new ArrayList<>();

    public String getRequestedType() {
        return requestedType;
    }

    public void setRequestedType(String requestedType) {
        this.requestedType = requestedType;
    }

    public String getRequestedProp() {
        return requestedProp;
    }

    public void setRequestedProp(String requestedProp) {
        this.requestedProp = requestedProp;
    }

    public Collection<SearchType> getSearchTypes() {
        return searchTypes;
    }

    public void setSearchTypes(Collection<SearchType> searchTypes) {
        this.searchTypes = searchTypes;
    }
}
