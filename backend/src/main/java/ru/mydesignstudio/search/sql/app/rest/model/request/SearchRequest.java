package ru.mydesignstudio.search.sql.app.rest.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.service.SearchType;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.service.value.ControlType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

public class SearchRequest {
    @NotNull
    @Size(min = 1)
    private Collection<SearchType> searchTypes = new ArrayList<>();
    @NotNull
    private LogicalOperation joinType;
    @Min(1)
    @Max(100)
    private int fuzzyMaskSize;
    @NotNull
    @Size(min = 1)
    private String selectFrom;
    @NotNull
    @Size(min = 1)
    private Collection<SearchAttribute> attributes = new ArrayList<>();

    public Collection<SearchType> getSearchTypes() {
        return searchTypes;
    }

    public void setSearchTypes(Collection<SearchType> searchTypes) {
        this.searchTypes = searchTypes;
    }

    public LogicalOperation getJoinType() {
        return joinType;
    }

    public void setJoinType(LogicalOperation joinType) {
        this.joinType = joinType;
    }

    public int getFuzzyMaskSize() {
        return fuzzyMaskSize;
    }

    public void setFuzzyMaskSize(int fuzzyMaskSize) {
        this.fuzzyMaskSize = fuzzyMaskSize;
    }

    public String getSelectFrom() {
        return selectFrom;
    }

    public void setSelectFrom(String selectFrom) {
        this.selectFrom = selectFrom;
    }

    public Collection<SearchAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Collection<SearchAttribute> attributes) {
        this.attributes = attributes;
    }

    public static class SearchAttribute {
        @NotNull
        @Size(min = 1)
        private String selectClass;
        @NotNull
        @Size(min = 1)
        private String property;
        @NotNull
        @JsonProperty("operation")
        private LogicalOperation logicalOperation;
        @NotNull
        @Size(min = 1)
        private ControlType valueType;
        @NotNull
        @Size(min = 1)
        private String value;

        public String getSelectClass() {
            return selectClass;
        }

        public void setSelectClass(String selectClass) {
            this.selectClass = selectClass;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public LogicalOperation getLogicalOperation() {
            return logicalOperation;
        }

        public void setLogicalOperation(LogicalOperation logicalOperation) {
            this.logicalOperation = logicalOperation;
        }

        public ControlType getValueType() {
            return valueType;
        }

        public void setValueType(ControlType valueType) {
            this.valueType = valueType;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
