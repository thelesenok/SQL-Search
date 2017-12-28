package ru.mydesignstudio.search.sql.app.service.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.service.model.definition.reader.ModelDefinitionReader;
import ru.mydesignstudio.search.sql.app.utils.Validations;

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
        Validations.assertTrue(StringUtils.isNoneBlank(typeName), "Type name not provided");

        return getModel().getTypes().stream()
                .filter(type -> typeName.equalsIgnoreCase(type.getTableName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(
                        "Can't find type by name %s",
                        typeName
                )));
    }

    @Override
    public PropertyDefinition findProperty(TypeDefinition typeDefinition, String propertyName) {
        Validations.assertNotNull(typeDefinition, "Type definition not provided");
        Validations.assertNotNull(StringUtils.isNoneBlank(propertyName), "Property name not provided");

        return typeDefinition.getProperties().stream()
                .filter(prop -> propertyName.equalsIgnoreCase(prop.getPropertyColumn()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(
                        "Can't find property by name %s",
                        propertyName
                )));
    }
}
