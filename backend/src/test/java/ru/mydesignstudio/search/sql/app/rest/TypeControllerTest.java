package ru.mydesignstudio.search.sql.app.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.rest.transformer.TypeTransformer;
import ru.mydesignstudio.search.sql.app.service.ModelService;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TypeController.class)
public class TypeControllerTest {
    @MockBean
    private TypeTransformer typeTransformer;
    @MockBean
    private ModelService modelService;
    @Autowired
    private MockMvc mvc;

    private TypeDefinition createDefinition(String typeName) {
        final TypeDefinition definition = new TypeDefinition();
        definition.setTypeName(typeName);
        return definition;
    }

    @Before
    public void setUp() throws Exception {
        doReturn(Arrays.asList(
                createDefinition("project"),
                createDefinition("executor"),
                createDefinition("stage")
        )).when(modelService).findAllTypes();
        //
        doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return (String) invocation.callRealMethod();
            }
        }).when(typeTransformer).transform(any(TypeDefinition.class));
    }

    @Test
    public void findAllTypes() throws Exception {
        mvc.perform(get("/types").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("project"))
                .andExpect(jsonPath("$[1]").value("executor"))
                .andExpect(jsonPath("$[2]").value("stage"));
    }

}