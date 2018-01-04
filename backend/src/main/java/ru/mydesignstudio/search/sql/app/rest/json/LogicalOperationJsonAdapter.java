package ru.mydesignstudio.search.sql.app.rest.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;

import java.io.IOException;

/**
 * Json adapter for logical operation.
 */
@JsonComponent
public class LogicalOperationJsonAdapter {
    public static final class Deserializer extends JsonDeserializer<LogicalOperation> {
        @Override
        public LogicalOperation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return LogicalOperation.byValue(p.getValueAsString());
        }
    }
}
