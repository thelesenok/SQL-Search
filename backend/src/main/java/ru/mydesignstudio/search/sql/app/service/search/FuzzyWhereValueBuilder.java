package ru.mydesignstudio.search.sql.app.service.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.request.SearchRequest;
import ru.mydesignstudio.search.sql.app.service.SearchType;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class FuzzyWhereValueBuilder {
    @Autowired
    private FuzzyValueBuilder valueBuilder;

    public String buildWhereValue(final SearchRequest request, final SearchRequest.SearchAttribute attribute) {
        Validations.assertNotNull(request, "Search request wasn't provided");
        Validations.assertNotNull(attribute, "Search attribute wasn't provided");
        Validations.assertTrue(
                request.getSearchTypes().contains(SearchType.FUZZY),
                "Fuzzy search type not selected"
        );

        final Collection<String> values = valueBuilder.buildValues(
                attribute.getValue(),
                request.getFuzzyMaskSize()
        );
        final Collection<String> parts = new LinkedList<>();
        boolean isFirst = true;
        for (String value : values) {
            final StringBuilder builder = new StringBuilder();
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(attribute.getSelectClass())
                        .append(".")
                        .append(attribute.getProperty());
            }
            builder.append(" LIKE ")
                    .append("'" + value + "'");
            parts.add(builder.toString());
        }
        return StringUtils.join(parts, " OR ");
    }
}
