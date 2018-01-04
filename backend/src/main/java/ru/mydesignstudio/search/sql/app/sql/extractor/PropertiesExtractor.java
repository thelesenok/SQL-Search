package ru.mydesignstudio.search.sql.app.sql.extractor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeReference;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PropertiesExtractor implements Extractor<Collection<PropertyDefinition>> {
    private static final Pattern PROP_PATTERN = Pattern.compile("\\`(.*)\\` \\b(int|varchar)\\b");

    @Autowired
    private PrimaryKeyExtractor primaryKeyExtractor;
    @Autowired
    private ForeignKeysExtractor foreignKeysExtractor;

    @Override
    public Collection<PropertyDefinition> extract(String sourceSql) {
        Validations.assertTrue(StringUtils.isNoneBlank(sourceSql), "SQL wasn't provided");

        final Collection<ForeignKeyInfo> foreignKeys = foreignKeysExtractor.extract(sourceSql);
        final String primaryKeyColumn = primaryKeyExtractor.extract(sourceSql);

        final Matcher matcher = PROP_PATTERN.matcher(sourceSql);
        final Collection<PropertyDefinition> properties = new ArrayList<>();
        while (matcher.find()) {
            final String columnName = matcher.group(1);
            final String columnType = matcher.group(2);

            final PropertyDefinition definition = new PropertyDefinition();
            definition.setPropertyName(columnName);
            definition.setPropertyColumn(columnName);
            definition.setPropertyType(getPropertyType(columnName, columnType, foreignKeys));
            definition.setPrimaryKey(columnName.equalsIgnoreCase(primaryKeyColumn));
            if (PropertyType.REFERENCE.equals(definition.getPropertyType())) {
                definition.setTypeReference(getTypeReference(columnName, foreignKeys));
            }
            properties.add(definition);
        }
        return properties;
    }

    private TypeReference getTypeReference(String columnName, Collection<ForeignKeyInfo> foreignKeys) {
        final TypeReference typeReference = new TypeReference();
        final ForeignKeyInfo foreignKey = foreignKeys.stream()
                .filter(fk -> columnName.equalsIgnoreCase(fk.getKeyColumn()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format(
                        "There is no reference for column %s",
                        columnName
                )));
        typeReference.setReferenceType(foreignKey.getKeyTable());
        return typeReference;
    }

    private PropertyType getPropertyType(String columnName, String columnType, Collection<ForeignKeyInfo> foreignKeys) {
        if ("varchar".equalsIgnoreCase(columnType)) {
            return PropertyType.STRING;
        } else if (isForeignKey(columnName, foreignKeys)) {
            return PropertyType.REFERENCE;
        } else if ("int".equalsIgnoreCase(columnType)) {
            return PropertyType.NUMBER;
        }
        throw new RuntimeException(String.format(
                "Unsupported type %s",
                columnType
        ));
    }

    private boolean isForeignKey(String columnName, Collection<ForeignKeyInfo> foreignKeys) {
        return foreignKeys.stream()
                .filter(fk -> columnName.equalsIgnoreCase(fk.getKeyColumn()))
                .findFirst()
                .orElse(null) != null;
    }
}
