package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.response.ControlResponse;
import ru.mydesignstudio.search.sql.app.service.value.ControlType;

@Component
public class ControlTypeTransformer implements Transformer<ControlType, ControlResponse> {
    @Override
    public ControlResponse transform(ControlType source) {
        final ControlResponse response = new ControlResponse();
        response.setControlType(source.getType());
        return response;
    }
}
