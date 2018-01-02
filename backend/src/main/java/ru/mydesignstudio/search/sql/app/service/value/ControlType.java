package ru.mydesignstudio.search.sql.app.service.value;

/**
 * Control types.
 */
public enum ControlType {
    /**
     * Select from list field type.
     */
    SELECT("select"),
    /**
     * Text field control.
     */
    TEXT_FIELD("input"),
    /**
     * Undefined control type.
     */
    UNDEFINED("undefined");

    private final String type;

    ControlType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static final ControlType byType(String type) {
        for (ControlType controlType : values()) {
            if (type.equalsIgnoreCase(controlType.getType())) {
                return controlType;
            }
        }
        return ControlType.UNDEFINED;
    }
}
