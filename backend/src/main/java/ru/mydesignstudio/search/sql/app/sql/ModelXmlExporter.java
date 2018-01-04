package ru.mydesignstudio.search.sql.app.sql;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeReference;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

@Component
public class ModelXmlExporter {
    public void export(ModelDefinition modelDefinition, File targetFile) {
        Validations.assertNotNull(modelDefinition, "Model definition wasn't provided");
        Validations.assertNotNull(targetFile, "Target file wasn't provided");

        try {
            final JAXBContext context = JAXBContext.newInstance(ModelDefinition.class,
                    PropertyDefinition.class,
                    PropertyType.class,
                    TypeDefinition.class,
                    TypeReference.class
            );
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(modelDefinition, targetFile);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
