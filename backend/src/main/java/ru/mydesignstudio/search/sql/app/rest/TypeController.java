package ru.mydesignstudio.search.sql.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mydesignstudio.search.sql.app.model.PropertyDefinition;
import ru.mydesignstudio.search.sql.app.model.TypeDefinition;
import ru.mydesignstudio.search.sql.app.rest.model.request.PropertyOperationsRequest;
import ru.mydesignstudio.search.sql.app.rest.model.request.TypePropertiesRequest;
import ru.mydesignstudio.search.sql.app.rest.model.response.PropertyResponse;
import ru.mydesignstudio.search.sql.app.rest.transformer.OperationTransformer;
import ru.mydesignstudio.search.sql.app.rest.transformer.PropertyTransformer;
import ru.mydesignstudio.search.sql.app.rest.transformer.TypeTransformer;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.service.operation.PropertyOperationsFinder;
import ru.mydesignstudio.search.sql.app.service.property.PropertyDefinitionFinder;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private ModelService modelService;
    @Autowired
    private PropertyDefinitionFinder propertyDefinitionFinder;
    @Autowired
    private PropertyOperationsFinder propertyOperationsFinder;
    @Autowired
    private TypeTransformer typeTransformer;
    @Autowired
    private PropertyTransformer propertyTransformer;
    @Autowired
    private OperationTransformer operationTransformer;

    @GetMapping
    public Collection<String> findAllTypes() {
        return modelService.findAllTypes()
                .stream()
                .map(typeTransformer::transform)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/properties")
    public Collection<PropertyResponse> findTypeProperties(@RequestBody @Validated TypePropertiesRequest request) {
        final TypeDefinition requestedType = modelService.findType(request.getRequestedType());
        final Collection<PropertyDefinition> properties = propertyDefinitionFinder.findProperties(
                requestedType, request.getSearchTypes()
        );
        return properties.stream()
                .map(propertyTransformer::transform)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/operations")
    public Collection<String> findPropertyOperations(@RequestBody @Validated PropertyOperationsRequest request) {
        final TypeDefinition requestedType = modelService.findType(request.getRequestedType());
        final PropertyDefinition property = modelService.findProperty(requestedType, request.getRequestedProp());
        final Collection<LogicalOperation> operations = propertyOperationsFinder.findOperations(property, request.getSearchTypes());
        return operations.stream()
                .map(operationTransformer::transform)
                .collect(Collectors.toList());
    }
}
