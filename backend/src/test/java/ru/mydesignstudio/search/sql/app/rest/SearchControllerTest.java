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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = AppApplication.class)
public class SearchControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void search1() throws Exception {
        final String request = JsonRequestReader.readFromFile("searchRequest_1.json");
        mvc.perform(
                post("/search")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(request)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.rows").isArray())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    public void search2() throws Exception {
        final String request = JsonRequestReader.readFromFile("searchRequest_2.json");
        mvc.perform(
                post("/search")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(request)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.rows").isArray())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    public void search3() throws Exception {
        final String request = JsonRequestReader.readFromFile("searchRequest_3.json");
        mvc.perform(
                post("/search")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(request)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.rows").isArray())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }
}