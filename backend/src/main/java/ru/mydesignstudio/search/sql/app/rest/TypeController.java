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
import ru.mydesignstudio.search.sql.app.rest.model.request.AvailableTypesRequest;
import ru.mydesignstudio.search.sql.app.rest.model.request.ControlTypeRequest;
import ru.mydesignstudio.search.sql.app.rest.model.request.PropertyOperationsRequest;
import ru.mydesignstudio.search.sql.app.rest.model.request.TypePropertiesRequest;
import ru.mydesignstudio.search.sql.app.rest.model.response.ControlResponse;
import ru.mydesignstudio.search.sql.app.rest.model.response.OperationResponse;
import ru.mydesignstudio.search.sql.app.rest.model.response.PropertyResponse;
import ru.mydesignstudio.search.sql.app.rest.model.response.TypeResponse;
import ru.mydesignstudio.search.sql.app.rest.transformer.ControlTypeTransformer;
import ru.mydesignstudio.search.sql.app.rest.transformer.LookupValuesTransformer;
import ru.mydesignstudio.search.sql.app.rest.transformer.OperationTransformer;
import ru.mydesignstudio.search.sql.app.rest.transformer.PropertyTransformer;
import ru.mydesignstudio.search.sql.app.rest.transformer.TypeTransformer;
import ru.mydesignstudio.search.sql.app.service.model.ModelService;
import ru.mydesignstudio.search.sql.app.service.operation.LogicalOperation;
import ru.mydesignstudio.search.sql.app.service.operation.PropertyOperationsFinder;
import ru.mydesignstudio.search.sql.app.service.property.PropertyDefinitionFinder;
import ru.mydesignstudio.search.sql.app.service.type.AvailableTypesFinder;
import ru.mydesignstudio.search.sql.app.service.value.ControlType;
import ru.mydesignstudio.search.sql.app.service.value.ControlTypeFinder;
import ru.mydesignstudio.search.sql.app.service.value.LookupValuesFinder;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private ModelService modelService;
    @Autowired
    private AvailableTypesFinder availableTypesFinder;
    @Autowired
    private PropertyDefinitionFinder propertyDefinitionFinder;
    @Autowired
    private PropertyOperationsFinder propertyOperationsFinder;
    @Autowired
    private ControlTypeFinder controlTypeFinder;
    @Autowired
    private LookupValuesFinder lookupValuesFinder;
    @Autowired
    private TypeTransformer typeTransformer;
    @Autowired
    private PropertyTransformer propertyTransformer;
    @Autowired
    private OperationTransformer operationTransformer;
    @Autowired
    private ControlTypeTransformer controlTypeTransformer;
    @Autowired
    private LookupValuesTransformer lookupValuesTransformer;

    @GetMapping
    public Collection<TypeResponse> findAllTypes() {
        return modelService.findAllTypes()
                .stream()
                .map(typeTransformer::transform)
                .collect(Collectors.toList());
    }

    @PostMapping("/available")
    public Collection<TypeResponse> findAvailableTypes(@RequestBody @Validated AvailableTypesRequest request) {
        final TypeDefinition typeDefinition = modelService.findType(request.getSelectType());
        final Collection<TypeDefinition> types = availableTypesFinder.findTypes(typeDefinition, request.getSearchTypes());
        return types
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
    public Collection<OperationResponse> findPropertyOperations(@RequestBody @Validated PropertyOperationsRequest request) {
        final TypeDefinition requestedType = modelService.findType(request.getRequestedType());
        final PropertyDefinition property = modelService.findProperty(requestedType, request.getRequestedProp());
        final Collection<LogicalOperation> operations = propertyOperationsFinder.findOperations(property, request.getSearchTypes());
        return operations.stream()
                .map(operationTransformer::transform)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/control")
    public ControlResponse findControlType(@RequestBody @Validated ControlTypeRequest request) {
        final TypeDefinition requestedType = modelService.findType(request.getRequestedType());
        final PropertyDefinition property = modelService.findProperty(requestedType, request.getRequestedProp());
        final ControlType controlType = controlTypeFinder.findControlType(
                requestedType,
                property,
                request.getRequestedOperation(),
                request.getSearchTypes()
        );
        final ControlResponse controlResponse = controlTypeTransformer.transform(controlType);
        if (ControlType.SELECT.equals(controlType)) {
            final Map<Integer, String> lookupValues = lookupValuesFinder.findLookupValues(property);
            controlResponse.setItems(lookupValuesTransformer.transform(lookupValues));
        }
        return controlResponse;
    }
}
