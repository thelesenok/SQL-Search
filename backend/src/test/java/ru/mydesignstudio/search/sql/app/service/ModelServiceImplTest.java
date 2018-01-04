package ru.mydesignstudio.search.sql.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mydesignstudio.search.sql.app.AppApplication;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = AppApplication.class
)
public class ModelServiceImplTest {
    @Autowired
    private ModelService modelService;

    @Test
    public void findAllTypes() throws Exception {
        final Collection<TypeDefinition> types = modelService.findAllTypes();
        assertThat(types).isNotNull();
        assertThat(types).isNotEmpty();
    }

    @Test
    public void allModelsWithPrimaryKeys() throws Exception {
        final Collection<TypeDefinition> allTypes = modelService.findAllTypes();
        final Collection<String> errorTypes = new ArrayList<>();
        for (TypeDefinition type : allTypes) {
            try {
                modelService.findPrimaryKeyProperty(type);
            } catch (Exception e) {
                errorTypes.add(type.getTypeName());
            }
        }
        assertTrue(String.format(
                "The following types are invalid: %s",
                errorTypes
        ), errorTypes.isEmpty());
    }

    @Test
    public void allModelsWithDisplayAttributes() throws Exception {
        final Collection<TypeDefinition> allTypes = modelService.findAllTypes();
        final Collection<String> errorTypes = new ArrayList<>();
        for (TypeDefinition type : allTypes) {
            try {
                modelService.findDisplayProperty(type);
            } catch (Exception e) {
                errorTypes.add(type.getTypeName());
            }
        }
        assertTrue(String.format(
                "The following types are invalid: %s",
                errorTypes
        ), errorTypes.isEmpty());
    }
}