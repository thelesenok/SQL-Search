package ru.mydesignstudio.search.sql.app.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.utils.Validations;

@Component
public class FromBuilder {
    @Autowired
    private ModelService modelService;

    public String buildFrom(SearchRequest request) {
        Validations.assertNotNull(request, "Search request wasn't provided");

        final TypeDefinition selectType = modelService.findType(request.getSelectFrom());
        return selectType.getTableName();
    }
}
