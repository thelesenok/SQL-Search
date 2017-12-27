package ru.mydesignstudio.search.sql.app.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ModelServiceImplTest {
    @Autowired
    private ModelService modelService;

    @Test
    public void findAllTypes() throws Exception {
        final Collection<TypeDefinition> types = modelService.findAllTypes();
        assertThat(types).isNotNull();
        assertThat(types).isNotEmpty();
    }

}