package ru.mydesignstudio.search.sql.app.rest.model;

/**
 * Property value types.
 */
public enum PropertyValueType {
    /**
     * String property.
     */
    STRING("string"),
    /**
     * Number property.
     */
    NUMBER("number"),
    /**
     * Reference property.
     */
    REFERENCE("reference");

    private final String value;

    PropertyValueType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
