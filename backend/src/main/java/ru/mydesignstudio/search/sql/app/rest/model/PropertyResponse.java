package ru.mydesignstudio.search.sql.app.rest.model;

/**
 * Property response model.
 */
public class PropertyResponse {
    /**
     * Type of property.
     */
    private PropertyValueType valueType;
    /**
     * Name of property, label for field.
     */
    private String label;
    /**
     * Actually, it's a column name.
     */
    private String value;

    public PropertyValueType getValueType() {
        return valueType;
    }

    public void setValueType(PropertyValueType valueType) {
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
