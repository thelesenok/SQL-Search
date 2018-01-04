package ru.mydesignstudio.search.sql.app.service.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.utils.JsonRequestReader;
import ru.mydesignstudio.search.sql.app.utils.SerializationUtils;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class QueryBuilderTest {
    @Autowired
    private QueryBuilder queryBuilder;

    @Test
    public void buildQuery() throws Exception {
        final String requestQueryString = JsonRequestReader.readFromFile("searchRequest_1.json");
        final SearchRequest searchRequest = SerializationUtils.fromJson(requestQueryString, SearchRequest.class);
        final String query = queryBuilder.buildQuery(searchRequest);
        assertThat(query).isNotBlank();
        System.out.println(query);
    }

}