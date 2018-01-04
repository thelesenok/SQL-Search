package ru.mydesignstudio.search.sql.app;

import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.PropertyType;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeReference;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SchemaGenerator {
    public void generate() throws Exception {
        final JAXBContext context = JAXBContext.newInstance(ModelDefinition.class,
                PropertyDefinition.class,
                PropertyType.class,
                TypeDefinition.class,
                TypeReference.class
        );
        final String schemaFilepath = getClass().getResource("/application.properties")
                .getFile()
                .replace("application.properties", "schema.xsd");
        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
                final OutputStream outputStream = new FileOutputStream(new File(schemaFilepath));
                final Result result = new StreamResult(outputStream);
                result.setSystemId(schemaFilepath);
                return result;
            }
        });
    }

    public static void main(String[] args) throws Exception {
        new SchemaGenerator().generate();
    }
}
