package ru.mydesignstudio.search.sql.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mydesignstudio.search.sql.app.rest.transformer.TypeTransformer;
import ru.mydesignstudio.search.sql.app.service.ModelService;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private ModelService modelService;
    @Autowired
    private TypeTransformer typeTransformer;

    @GetMapping
    public Collection<String> findAllTypes() {
        return modelService.findAllTypes()
                .stream()
                .map(typeTransformer::transform)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/{type}") // todo implement it
    public Collection<String> findTypeProperties() {
        return Collections.emptyList();
    }
}
