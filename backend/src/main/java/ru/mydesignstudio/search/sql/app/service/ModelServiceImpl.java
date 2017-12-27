package ru.mydesignstudio.search.sql.app.service;

import org.springframework.stereotype.Service;
import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeReference;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.util.Collection;

@Service
public class ModelServiceImpl implements ModelService {
    private ModelDefinition getModel() {
        try {
            final JAXBContext context = JAXBContext.newInstance(
                    ModelDefinition.class,
                    PropertyDefinition.class,
                    PropertyType.class,
                    TypeDefinition.class,
                    TypeReference.class
            );
            final URL modelUrl = getClass().getResource("/model/model.xml");
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            return (ModelDefinition) unmarshaller.unmarshal(modelUrl);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<TypeDefinition> findAllTypes() {
        return getModel().getTypes();
    }
}
