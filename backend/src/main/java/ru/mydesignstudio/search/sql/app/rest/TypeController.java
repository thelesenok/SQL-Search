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
import ru.mydesignstudio.search.sql.app.rest.model.PropertyResponse;
import ru.mydesignstudio.search.sql.app.rest.model.TypePropertyRequest;
import ru.mydesignstudio.search.sql.app.rest.transformer.PropertyTransformer;
import ru.mydesignstudio.search.sql.app.rest.transformer.TypeTransformer;
import ru.mydesignstudio.search.sql.app.service.ModelService;
import ru.mydesignstudio.search.sql.app.service.PropertyDefinitionFinder;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private ModelService modelService;
    @Autowired
    private PropertyDefinitionFinder propertyDefinitionFinder;
    @Autowired
    private TypeTransformer typeTransformer;
    @Autowired
    private PropertyTransformer propertyTransformer;

    @GetMapping
    public Collection<String> findAllTypes() {
        return modelService.findAllTypes()
                .stream()
                .map(typeTransformer::transform)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/properties")
    public Collection<PropertyResponse> findTypeProperties(@RequestBody @Validated TypePropertyRequest request) {
        final TypeDefinition requestedType = modelService.findType(request.getRequestedType());
        final Collection<PropertyDefinition> properties = propertyDefinitionFinder.findDefinitions(
                requestedType, request.getSearchTypes()
        );
        return properties.stream()
                .map(propertyTransformer::transform)
                .collect(Collectors.toList());
    }
}
