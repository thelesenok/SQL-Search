package ru.mydesignstudio.search.sql.app.service.operation;

/**
 * Logical operation.
 */
public enum LogicalOperation {
    EQUALS("equals"),
    LIKE("like"),
    FUZZY_LIKE("fuzzy_like"),
    CONTAINS("contains"),
    EQUALS_NOT("equals_not"),
    STARTS_WITH("starts_with"),
    ENDS_WITH("ends_with"),
    MORE_THAN("more_than"),
    LESS_THAN("less_than"),
    MORE_OR_EQUALS("more_or_equals"),
    LESS_OR_EQUALS("less_or_equals"),
    AND("and"),
    OR("or");

    private final String value;

    LogicalOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
