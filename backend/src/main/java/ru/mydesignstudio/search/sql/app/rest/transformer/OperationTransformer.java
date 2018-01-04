package ru.mydesignstudio.search.sql.app.rest.transformer;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.search.sql.app.rest.model.response.OperationResponse;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;

@Component
public class OperationTransformer implements Transformer<LogicalOperation, OperationResponse> {
    @Override
    public OperationResponse transform(LogicalOperation source) {
        final OperationResponse operationResponse = new OperationResponse();
        operationResponse.setLabel(source.name());
        operationResponse.setValue(source.getValue());
        return operationResponse;
    }
}
