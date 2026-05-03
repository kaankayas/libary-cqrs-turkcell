package com.turkcell.libary_cqrs.web.controllers;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.turkcell.libary_cqrs.application.features.Author.Create.CreateAuthorCommand;
import com.turkcell.libary_cqrs.application.features.Author.Update.UpdateAuthorCommand;
import com.turkcell.libary_cqrs.application.features.Author.Delete.DeleteAuthorCommand;
import com.turkcell.libary_cqrs.application.features.Author.GetById.GetByIdAuthorQuery;
import com.turkcell.libary_cqrs.application.features.Author.GetById.GetByIdAuthorResponse;
import com.turkcell.libary_cqrs.application.features.Author.GetAll.GetAllAuthorQuery;
import com.turkcell.libary_cqrs.application.features.Author.GetAll.GetAllAuthorQueryResponse;
import com.turkcell.libary_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {
    private final Mediator mediator;

    public AuthorsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody CreateAuthorCommand command) {

        return ResponseEntity.status(HttpStatus.CREATED).body(mediator.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody UpdateAuthorCommand command) {

        if (!id.equals(command.id())) return ResponseEntity.badRequest().build();
        mediator.send(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        mediator.send(new DeleteAuthorCommand(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdAuthorResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mediator.send(new GetByIdAuthorQuery(id)));
    }

    @GetMapping
    public ResponseEntity<GetAllAuthorQueryResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mediator.send(new GetAllAuthorQuery(page, size)));
    }
}
