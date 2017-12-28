package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.response.SearchResponse;

import java.util.Collection;

@Component
public class SearchResponseTransformer implements Transformer<Collection<Collection<String>>, SearchResponse> {
    @Override
    public SearchResponse transform(Collection<Collection<String>> source) {
        final SearchResponse response = new SearchResponse();
        response.setRows(source);
        return response;
    }
}
