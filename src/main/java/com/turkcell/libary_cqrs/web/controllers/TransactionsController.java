package com.turkcell.libary_cqrs.web.controllers;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.turkcell.libary_cqrs.application.features.Transaction.Create.CreateTransactionCommand;
import com.turkcell.libary_cqrs.application.features.Transaction.Update.UpdateTransactionCommand;
import com.turkcell.libary_cqrs.application.features.Transaction.Delete.DeleteTransactionCommand;
import com.turkcell.libary_cqrs.application.features.Transaction.GetById.GetByIdTransactionQuery;
import com.turkcell.libary_cqrs.application.features.Transaction.GetById.GetByIdTransactionResponse;
import com.turkcell.libary_cqrs.application.features.Transaction.GetAll.GetAllTransactionQuery;
import com.turkcell.libary_cqrs.application.features.Transaction.GetAll.GetAllTransactionQueryResponse;
import com.turkcell.libary_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {
    private final Mediator mediator;

    public TransactionsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody CreateTransactionCommand command) {

        return ResponseEntity.status(HttpStatus.CREATED).body(mediator.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody UpdateTransactionCommand command) {

        if (!id.equals(command.id())) return ResponseEntity.badRequest().build();
        mediator.send(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        mediator.send(new DeleteTransactionCommand(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdTransactionResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mediator.send(new GetByIdTransactionQuery(id)));
    }

    @GetMapping
    public ResponseEntity<GetAllTransactionQueryResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mediator.send(new GetAllTransactionQuery(page, size)));
    }
}
