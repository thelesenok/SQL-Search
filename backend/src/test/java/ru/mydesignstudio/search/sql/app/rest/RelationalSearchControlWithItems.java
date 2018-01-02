package ru.mydesignstudio.search.sql.app.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.mydesignstudio.search.sql.app.AppApplication;
import ru.mydesignstudio.search.sql.app.utils.JsonRequestReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = AppApplication.class)
public class RelationalSearchControlWithItems {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testControl() throws Exception {
        final String request = JsonRequestReader.readFromFile("availableControlRequest_1.json");
        mvc.perform(
                post("/types/control")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items").isNotEmpty())
                .andExpect(jsonPath("$.controlType").value("select"));
    }
}
