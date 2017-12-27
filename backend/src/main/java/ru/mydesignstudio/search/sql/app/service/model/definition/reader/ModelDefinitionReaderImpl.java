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
import java.net.URL;

@Component
public class ModelDefinitionReaderImpl implements ModelDefinitionReader {
    /**
     * {@inheritDoc}
     */
    @Override
    public ModelDefinition read(File file) {
        Validations.assertNotNull(file, "Configuration file not provided");

        try {
            final JAXBContext context = JAXBContext.newInstance(
                    ModelDefinition.class,
                    PropertyDefinition.class,
                    PropertyType.class,
                    TypeDefinition.class,
                    TypeReference.class
            );
            final URL modelUrl = file.toURI().toURL();
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            return (ModelDefinition) unmarshaller.unmarshal(modelUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
