package ru.mydesignstudio.search.sql.app.service.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.service.model.definition.reader.ModelDefinitionReader;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

@Service
public class ModelServiceImpl implements ModelService {
    @Value("${model.filepath}")
    private String modelFilename;
    @Autowired
    private ModelDefinitionReader definitionReader;

    private ModelDefinition getModel() {
        try (final InputStream inputStream = new ClassPathResource(modelFilename).getInputStream()) {
            return definitionReader.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(String.format(
                    "Can't load model definition file from %s",
                    modelFilename
            ));
        }
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

    @Override
    public PropertyDefinition findDisplayProperty(TypeDefinition typeDefinition) {
        Validations.assertNotNull(typeDefinition, "Type definition wasn't provided");

        return typeDefinition.getProperties().stream()
                .filter(prop -> prop.isDisplayProperty())
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(
                        "Type %s has no display property",
                        typeDefinition.getTypeName()
                )));
    }

    @Override
    public PropertyDefinition findPrimaryKeyProperty(TypeDefinition typeDefinition) {
        Validations.assertNotNull(typeDefinition, "Type definition wasn't provided");

        return typeDefinition.getProperties().stream()
                .filter(prop -> prop.isPrimaryKey())
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(
                        "Type %s has no primary key property",
                        typeDefinition.getTypeName()
                )));
    }

    @Override
    public PropertyDefinition findRelationProperty(TypeDefinition sourceType, TypeDefinition targetType) {
        Validations.assertNotNull(sourceType, "Source type wasn't provided");
        Validations.assertNotNull(targetType, "Target type definition wasn't provided");

        return sourceType.getProperties().stream()
                .filter(prop -> PropertyType.REFERENCE.equals(prop.getPropertyType()))
                .filter(prop -> findType(prop.getTypeReference().getReferenceType()).equals(targetType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(
                        "There is no relation property for types %s and %s",
                        sourceType,
                        targetType
                )));
    }
}
