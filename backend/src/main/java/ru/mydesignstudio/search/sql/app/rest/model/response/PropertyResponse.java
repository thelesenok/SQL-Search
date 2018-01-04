package ru.mydesignstudio.search.sql.app.rest.model.response;

import ru.mydesignstudio.search.sql.app.model.PropertyType;

/**
 * Property response model.
 */
public class PropertyResponse {
    /**
     * Type of property.
     */
    private PropertyType valueType;
    /**
     * Name of property, label for field.
     */
    private String label;
    /**
     * Actually, it's a column name.
     */
    private String value;

    public PropertyType getValueType() {
        return valueType;
    }

    public void setValueType(PropertyType valueType) {
        this.valueType = valueType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
