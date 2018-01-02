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
    TEXT_FIELD("input");

    private final String type;

    ControlType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
