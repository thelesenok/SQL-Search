package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.response.ControlResponse;
import ru.mydesignstudio.search.sql.app.utils.Validations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
public class LookupValuesTransformer implements Transformer<Map<Integer, String>, Collection<ControlResponse.SelectItem>> {
    @Override
    public Collection<ControlResponse.SelectItem> transform(Map<Integer, String> source) {
        Validations.assertNotNull(source, "Source items wasn't provided");

        final Collection<ControlResponse.SelectItem> result = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : source.entrySet()) {
            final ControlResponse.SelectItem selectItem = new ControlResponse.SelectItem(
                    entry.getValue(),
                    String.valueOf(entry.getKey())
            );
            result.add(selectItem);
        }
        return result;
    }
}
