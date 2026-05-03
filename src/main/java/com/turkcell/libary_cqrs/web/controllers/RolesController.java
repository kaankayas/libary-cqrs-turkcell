package com.turkcell.libary_cqrs.web.controllers;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.turkcell.libary_cqrs.application.features.Role.Create.CreateRoleCommand;
import com.turkcell.libary_cqrs.application.features.Role.Update.UpdateRoleCommand;
import com.turkcell.libary_cqrs.application.features.Role.Delete.DeleteRoleCommand;
import com.turkcell.libary_cqrs.application.features.Role.GetById.GetByIdRoleQuery;
import com.turkcell.libary_cqrs.application.features.Role.GetById.GetByIdRoleResponse;
import com.turkcell.libary_cqrs.application.features.Role.GetAll.GetAllRoleQuery;
import com.turkcell.libary_cqrs.application.features.Role.GetAll.GetAllRoleQueryResponse;
import com.turkcell.libary_cqrs.core.mediator.Mediator;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    private final Mediator mediator;

    public RolesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody CreateRoleCommand command) {

        return ResponseEntity.status(HttpStatus.CREATED).body(mediator.send(command));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody UpdateRoleCommand command) {

        if (!id.equals(command.id())) return ResponseEntity.badRequest().build();
        mediator.send(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        mediator.send(new DeleteRoleCommand(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdRoleResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mediator.send(new GetByIdRoleQuery(id)));
    }

    @GetMapping
    public ResponseEntity<GetAllRoleQueryResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mediator.send(new GetAllRoleQuery(page, size)));
    }
}
