package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;

@Component
public class OperationTransformer implements Transformer<LogicalOperation, String> {
    @Override
    public String transform(LogicalOperation source) {
        return source.getValue();
    }
}
