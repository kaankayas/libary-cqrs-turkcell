package com.turkcell.libary_cqrs.web.controllers;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.turkcell.libary_cqrs.application.features.Category.Create.CreateCategoryCommand;
import com.turkcell.libary_cqrs.application.features.Category.Update.UpdateCategoryCommand;
import com.turkcell.libary_cqrs.application.features.Category.Delete.DeleteCategoryCommand;
import com.turkcell.libary_cqrs.application.features.Category.GetById.GetByIdCategoryQuery;
import com.turkcell.libary_cqrs.application.features.Category.GetById.GetByIdCategoryResponse;
import com.turkcell.libary_cqrs.application.features.Category.GetAll.GetAllCategoryQuery;
import com.turkcell.libary_cqrs.application.features.Category.GetAll.GetAllCategoryQueryResponse;
import com.turkcell.libary_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final Mediator mediator;

    public CategoriesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody CreateCategoryCommand command) {

        return ResponseEntity.status(HttpStatus.CREATED).body(mediator.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody UpdateCategoryCommand command) {

        if (!id.equals(command.id())) return ResponseEntity.badRequest().build();
        mediator.send(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        mediator.send(new DeleteCategoryCommand(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdCategoryResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mediator.send(new GetByIdCategoryQuery(id)));
    }

    @GetMapping
    public ResponseEntity<GetAllCategoryQueryResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mediator.send(new GetAllCategoryQuery(page, size)));
    }
}
