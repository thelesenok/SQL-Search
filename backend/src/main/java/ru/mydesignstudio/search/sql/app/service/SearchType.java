package ru.mydesignstudio.search.sql.app.service;

/**
 * Search type.
 */
public enum SearchType {
    /**
     * Attributive search type.
     */
    ATTRIBUTIVE("attributive"),
    /**
     * Relational search type.
     */
    RELATIONAL("relational"),
    /**
     * Fuzzy-like search type.
     */
    FUZZY("fuzzy"),
    /**
     * Undefined search type.
     */
    UNDEFINED("undefined");

    private final String type;

    SearchType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    /**
     * Get search type by string value.
     *
     * @param type string type
     * @return search type
     */
    public static SearchType byType(String type) {
        for (SearchType searchType : SearchType.values()) {
            if (type.equalsIgnoreCase(searchType.getType())) {
                return searchType;
            }
        }
        return UNDEFINED;
    }
}
