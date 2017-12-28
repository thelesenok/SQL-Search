package ru.mydesignstudio.search.sql.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.rest.model.response.SearchResponse;
import ru.mydesignstudio.search.sql.app.rest.transformer.SearchResponseTransformer;
import ru.mydesignstudio.search.sql.app.service.search.QueryBuilder;
import ru.mydesignstudio.search.sql.app.service.search.SearchService;

import java.util.Collection;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private QueryBuilder queryBuilder;
    @Autowired
    private SearchService searchService;
    @Autowired
    private SearchResponseTransformer responseTransformer;

    @PostMapping
    public SearchResponse search(@RequestBody @Validated SearchRequest request) {
        final String query = queryBuilder.buildQuery(request);
        final Collection<Collection<String>> results = searchService.search(query);
        return responseTransformer.transform(results);
    }
}
