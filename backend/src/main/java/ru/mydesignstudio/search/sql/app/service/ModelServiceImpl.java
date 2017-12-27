package ru.mydesignstudio.search.sql.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeReference;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;
import java.util.Collection;

@Service
public class ModelServiceImpl implements ModelService {
    @Value("${model.filepath}")
    private String modelFilename;
    @Autowired
    private ModelDefinitionReader definitionReader;

    private ModelDefinition getModel() {
        final URL resource = getClass().getResource(modelFilename);
        return definitionReader.read(new File(
                resource.getFile()
        ));
    }

    @Override
    public Collection<TypeDefinition> findAllTypes() {
        return getModel().getTypes();
    }

    @Override
    public TypeDefinition findType(String typeName) {
        return getModel().getTypes().stream()
                .filter(type -> typeName.equalsIgnoreCase(type.getTypeName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(
                        "Can't find type by name %s",
                        typeName
                )));
    }
}
