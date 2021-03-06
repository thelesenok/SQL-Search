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
import ru.mydesignstudio.search.sql.app.AppApplication;
import ru.mydesignstudio.search.sql.app.utils.JsonRequestReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = AppApplication.class)
public class RelationalSearchAvailableProperties {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testCustomers() throws Exception {
        final String request = JsonRequestReader.readFromFile("availableTypesRequest_1.json");
        mvc.perform(
            post("/types/available")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }
}
