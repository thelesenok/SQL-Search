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
    NUMBER("number"),
    /**
     * Undefined type.
     */
    UNDEFINED("undefined");

    private final String type;

    PropertyType(String type) {
        this.type = type;
    }

    public static final PropertyType byType(final String type) {
        for (PropertyType propertyType : PropertyType.values()) {
            if (type.equalsIgnoreCase(propertyType.getType())) {
                return propertyType;
            }
        }
        return UNDEFINED;
    }

    public String getType() {
        return type;
    }
}
