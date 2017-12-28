package ru.mydesignstudio.search.sql.app.rest.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import ru.mydesignstudio.search.sql.app.model.PropertyType;

import java.io.IOException;

/**
 * Json serialization helper for PropertyValueType
 */
@JsonComponent
public class PropertyTypeJsonAdapter {
    public static final class Serializer extends JsonSerializer<PropertyType> {
        @Override
        public void serialize(PropertyType value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(value.getType());
        }
    }

    public static final class Deserializer extends JsonDeserializer<PropertyType> {
        @Override
        public PropertyType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return PropertyType.byType(p.getValueAsString());
        }
    }
}
