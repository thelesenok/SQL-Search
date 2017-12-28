package ru.mydesignstudio.search.sql.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.rest.json.LogicalOperationJsonAdapter;
import ru.mydesignstudio.search.sql.app.rest.json.PropertyTypeJsonAdapter;
import ru.mydesignstudio.search.sql.app.rest.json.SearchTypeJsonAdapter;
import ru.mydesignstudio.search.sql.app.service.SearchType;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;

import java.io.IOException;

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

    public static final <T> T fromJson(String json, Class<T> clazz) {
        final ObjectMapper objectMapper = new ObjectMapper();

        final SimpleModule logicalOperationAdapter = new SimpleModule();
        logicalOperationAdapter.addDeserializer(LogicalOperation.class, new LogicalOperationJsonAdapter.Deserializer());
        objectMapper.registerModule(logicalOperationAdapter);

        final SimpleModule searchTypeAdapter = new SimpleModule();
        searchTypeAdapter.addDeserializer(SearchType.class, new SearchTypeJsonAdapter.Deserializer());
        objectMapper.registerModule(searchTypeAdapter);

        final SimpleModule propertyTypeAdapter = new SimpleModule();
        propertyTypeAdapter.addDeserializer(PropertyType.class, new PropertyTypeJsonAdapter.Deserializer());
        objectMapper.registerModule(propertyTypeAdapter);

        try {
            return objectMapper.readerFor(clazz)
                    .readValue(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
