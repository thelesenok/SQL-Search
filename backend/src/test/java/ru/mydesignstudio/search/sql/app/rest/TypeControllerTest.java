package ru.mydesignstudio.search.sql.app.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.utils.JsonRequestReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(properties = {
        "model.filepath=/model/test_types.xml"
})
public class TypeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void findAllTypes() throws Exception {
        mvc.perform(get("/types").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].value").value("PROJECTS"))
                .andExpect(jsonPath("$[1].value").value("EXECUTORS"))
                .andExpect(jsonPath("$[2].value").value("STAGES"));
    }

    @Test
    public void findTypePropertiesForAttributiveSearch() throws Exception {
        final String requestString = JsonRequestReader.readFromFile("propertiesRequest_1.json");
        mvc.perform(
                post("/types/properties")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].label").value("string-property"))
                .andExpect(jsonPath("$[0].value").value("STRING_COLUMN"))
                .andExpect(jsonPath("$[0].valueType").value("string"));
    }

    @Test
    public void findTypePropertiesForRelationalSearch() throws Exception {
        final String requestString = JsonRequestReader.readFromFile("propertiesRequest_2.json");
        mvc.perform(
                post("/types/properties")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].label").value("string-property"))
                .andExpect(jsonPath("$[0].value").value("STRING_COLUMN"))
                .andExpect(jsonPath("$[0].valueType").value("string"));
    }

    @Test
    public void findOperationsForAttributiveSearch() throws Exception {
        final String requestString = JsonRequestReader.readFromFile("operationsRequest_1.json");
        mvc.perform(
                post("/types/operations")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].value").value(LogicalOperation.CONTAINS.getValue()))
                .andExpect(jsonPath("$[1].value").value(LogicalOperation.EQUALS.getValue()))
                .andExpect(jsonPath("$[2].value").value(LogicalOperation.STARTS_WITH.getValue()))
                .andExpect(jsonPath("$[3].value").value(LogicalOperation.ENDS_WITH.getValue()))
                .andExpect(jsonPath("$[0].label").value(LogicalOperation.CONTAINS.name()))
                .andExpect(jsonPath("$[1].label").value(LogicalOperation.EQUALS.name()))
                .andExpect(jsonPath("$[2].label").value(LogicalOperation.STARTS_WITH.name()))
                .andExpect(jsonPath("$[3].label").value(LogicalOperation.ENDS_WITH.name()));
    }

    @Test
    public void findOperationsForRelationalSearch() throws Exception {
        final String requestString = JsonRequestReader.readFromFile("operationsRequest_2.json");
        mvc.perform(
                post("/types/operations")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].value").value(LogicalOperation.CONTAINS.getValue()))
                .andExpect(jsonPath("$[1].value").value(LogicalOperation.EQUALS.getValue()))
                .andExpect(jsonPath("$[2].value").value(LogicalOperation.STARTS_WITH.getValue()))
                .andExpect(jsonPath("$[3].value").value(LogicalOperation.ENDS_WITH.getValue()))
                .andExpect(jsonPath("$[0].label").value(LogicalOperation.CONTAINS.name()))
                .andExpect(jsonPath("$[1].label").value(LogicalOperation.EQUALS.name()))
                .andExpect(jsonPath("$[2].label").value(LogicalOperation.STARTS_WITH.name()))
                .andExpect(jsonPath("$[3].label").value(LogicalOperation.ENDS_WITH.name()));
    }

    @Test
    public void findOperationsForFuzzySearch() throws Exception {
        final String requestString = JsonRequestReader.readFromFile("operationsRequest_3.json");
        mvc.perform(
                post("/types/operations")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].value").value(LogicalOperation.CONTAINS.getValue()))
                .andExpect(jsonPath("$[1].value").value(LogicalOperation.EQUALS.getValue()))
                .andExpect(jsonPath("$[2].value").value(LogicalOperation.STARTS_WITH.getValue()))
                .andExpect(jsonPath("$[3].value").value(LogicalOperation.ENDS_WITH.getValue()))
                .andExpect(jsonPath("$[4].value").value(LogicalOperation.FUZZY_LIKE.getValue()))
                .andExpect(jsonPath("$[0].label").value(LogicalOperation.CONTAINS.name()))
                .andExpect(jsonPath("$[1].label").value(LogicalOperation.EQUALS.name()))
                .andExpect(jsonPath("$[2].label").value(LogicalOperation.STARTS_WITH.name()))
                .andExpect(jsonPath("$[3].label").value(LogicalOperation.ENDS_WITH.name()))
                .andExpect(jsonPath("$[4].label").value(LogicalOperation.FUZZY_LIKE.name()));
    }

    @Test
    public void findControlType() throws Exception {
        final String requestString = JsonRequestReader.readFromFile("controlTypeRequest_1.json");
        mvc.perform(
                post("/types/control")
                        .content(requestString)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("controlType").value("text"));
    }
}