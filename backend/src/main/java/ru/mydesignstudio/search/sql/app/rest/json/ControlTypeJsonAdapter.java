package ru.mydesignstudio.search.sql.app.rest.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;
import ru.mydesignstudio.search.sql.app.service.value.ControlType;

import java.io.IOException;

@JsonComponent
public class ControlTypeJsonAdapter {
    public static final class Deserializer extends JsonDeserializer<ControlType> {
        @Override
        public ControlType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return ControlType.byType(p.getValueAsString());
        }
    }
}
