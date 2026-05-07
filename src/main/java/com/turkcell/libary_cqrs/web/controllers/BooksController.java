package com.turkcell.libary_cqrs.web.controllers;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.turkcell.libary_cqrs.application.features.Book.Create.CreateBookCommand;
import com.turkcell.libary_cqrs.application.features.Book.Update.UpdateBookCommand;
import com.turkcell.libary_cqrs.application.features.Book.Delete.DeleteBookCommand;
import com.turkcell.libary_cqrs.application.features.Book.GetById.GetByIdBookQuery;
import com.turkcell.libary_cqrs.application.features.Book.GetById.GetByIdBookResponse;
import com.turkcell.libary_cqrs.application.features.Book.GetAll.GetAllBookQuery;
import com.turkcell.libary_cqrs.application.features.Book.GetAll.GetAllBookQueryResponse;
import com.turkcell.libary_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final Mediator mediator;

    public BooksController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody CreateBookCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mediator.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UpdateBookCommand command) {
        if (!id.equals(command.id())) return ResponseEntity.badRequest().build();
        mediator.send(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        mediator.send(new DeleteBookCommand(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdBookResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mediator.send(new GetByIdBookQuery(id)));
    }

    @GetMapping
    public ResponseEntity<GetAllBookQueryResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mediator.send(new GetAllBookQuery(page, size)));
    }
}
