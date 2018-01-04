package ru.mydesignstudio.search.sql.app.utils;

public class Validations {
    public static final void assertNotNull(Object value, String message) {
        if (value == null) {
            throw new RuntimeException(message);
        }
    }

    public static final void assertTrue(boolean value, String message) {
        if (!value) {
            throw new RuntimeException(message);
        }
    }
}
