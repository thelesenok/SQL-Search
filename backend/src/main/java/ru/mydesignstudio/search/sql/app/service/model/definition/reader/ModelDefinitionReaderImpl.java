package ru.mydesignstudio.search.sql.app.service.model.definition.reader;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeReference;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

@Component
public class ModelDefinitionReaderImpl implements ModelDefinitionReader {
    /**
     * {@inheritDoc}
     */
    @Override
    public ModelDefinition read(final InputStream inputStream) {
        Validations.assertNotNull(inputStream, "Configuration file not provided");

        try {
            final JAXBContext context = JAXBContext.newInstance(
                    ModelDefinition.class,
                    PropertyDefinition.class,
                    PropertyType.class,
                    TypeDefinition.class,
                    TypeReference.class
            );
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            return (ModelDefinition) unmarshaller.unmarshal(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
