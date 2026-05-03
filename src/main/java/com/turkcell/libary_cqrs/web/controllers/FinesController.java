package com.turkcell.libary_cqrs.web.controllers;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.turkcell.libary_cqrs.application.features.Fine.Create.CreateFineCommand;
import com.turkcell.libary_cqrs.application.features.Fine.Update.UpdateFineCommand;
import com.turkcell.libary_cqrs.application.features.Fine.Delete.DeleteFineCommand;
import com.turkcell.libary_cqrs.application.features.Fine.GetById.GetByIdFineQuery;
import com.turkcell.libary_cqrs.application.features.Fine.GetById.GetByIdFineResponse;
import com.turkcell.libary_cqrs.application.features.Fine.GetAll.GetAllFineQuery;
import com.turkcell.libary_cqrs.application.features.Fine.GetAll.GetAllFineQueryResponse;
import com.turkcell.libary_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/fines")
public class FinesController {
    private final Mediator mediator;

    public FinesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody CreateFineCommand command) {

        return ResponseEntity.status(HttpStatus.CREATED).body(mediator.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody UpdateFineCommand command) {

        if (!id.equals(command.id())) return ResponseEntity.badRequest().build();
        mediator.send(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        mediator.send(new DeleteFineCommand(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdFineResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mediator.send(new GetByIdFineQuery(id)));
    }

    @GetMapping
    public ResponseEntity<GetAllFineQueryResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mediator.send(new GetAllFineQuery(page, size)));
    }
}
