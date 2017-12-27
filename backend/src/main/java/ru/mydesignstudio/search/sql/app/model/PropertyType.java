package ru.mydesignstudio.search.sql.app.model;

/**
 * Property types like String property, int property, relation property
 * and so on. 
 * 
 * @author Aleksandr_Barmin
 *
 */
public enum PropertyType {
    /**
     * Reference type.
     */
    REFERENCE("reference"),
    /**
     * String type.
     */
    STRING("string"),
    /**
     * Number type.
     */
    NUMBER("number");

    private final String type;

    PropertyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
