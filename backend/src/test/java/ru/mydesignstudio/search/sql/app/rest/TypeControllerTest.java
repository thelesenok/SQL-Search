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
                .andExpect(jsonPath("$[0]").value("project"))
                .andExpect(jsonPath("$[1]").value("executor"))
                .andExpect(jsonPath("$[2]").value("stage"));
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
}