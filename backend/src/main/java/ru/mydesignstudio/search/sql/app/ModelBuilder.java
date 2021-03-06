package ru.mydesignstudio.search.sql.app;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.mydesignstudio.search.sql.app.model.ModelDefinition;
import ru.mydesignstudio.search.sql.app.sql.ModelXmlExporter;
import ru.mydesignstudio.search.sql.app.sql.SqlModelGenerator;

import java.io.File;
import java.net.URL;

// @SpringBootApplication
public class ModelBuilder {
    public static void main(String[] args) throws Exception {
        final ConfigurableApplicationContext context =
                SpringApplication.run(ModelBuilder.class, args);

        final SqlModelGenerator modelGenerator = context.getBean(SqlModelGenerator.class);
        final ModelXmlExporter xmlExporter = context.getBean(ModelXmlExporter.class);

        final URL sourceUrl = ModelBuilder.class.getResource("/model/source/source.xml");
        final String targetFilePath =
                sourceUrl.getFile().replace("/source/source.xml", "/model.xml");
        final ModelDefinition modelDefinition = modelGenerator.generate(new File(sourceUrl.toURI()));
        xmlExporter.export(modelDefinition, new File(targetFilePath));

        context.close();
    }
}
