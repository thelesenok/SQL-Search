package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.service.value.ControlType;

@Component
public class ControlTypeTransformer implements Transformer<ControlType, String> {
    @Override
    public String transform(ControlType source) {
        return source.getType();
    }
}
