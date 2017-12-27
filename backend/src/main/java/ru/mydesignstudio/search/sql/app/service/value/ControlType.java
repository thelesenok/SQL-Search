package ru.mydesignstudio.search.sql.app.service.value;

/**
 * Control types.
 */
public enum ControlType {
    /**
     * Text field control.
     */
    TEXT_FIELD("text");

    private final String type;

    ControlType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
