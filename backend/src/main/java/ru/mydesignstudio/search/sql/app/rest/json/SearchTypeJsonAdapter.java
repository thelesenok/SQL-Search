package ru.mydesignstudio.search.sql.app.rest.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;
import ru.mydesignstudio.search.sql.app.service.SearchType;

import java.io.IOException;

/**
 * Json serialization helper for search type enumeration.
 */
@JsonComponent
public class SearchTypeJsonAdapter {
    public static class Deserializer extends JsonDeserializer<SearchType> {
        @Override
        public SearchType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return SearchType.byType(p.getValueAsString());
        }
    }
}
