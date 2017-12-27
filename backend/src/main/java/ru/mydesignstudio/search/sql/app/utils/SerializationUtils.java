package ru.mydesignstudio.search.sql.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationUtils {
    /**
     * Serialize value to json string.
     * @param value object to serialize
     * @return json string
     */
    public static final String toJson(Object value) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
