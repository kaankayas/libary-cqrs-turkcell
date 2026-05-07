package com.turkcell.libary_cqrs.web.controllers;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.turkcell.libary_cqrs.application.features.Staff.Update.UpdateStaffCommand;
import com.turkcell.libary_cqrs.application.features.Staff.Delete.DeleteStaffCommand;
import com.turkcell.libary_cqrs.application.features.Staff.GetById.GetByIdStaffQuery;
import com.turkcell.libary_cqrs.application.features.Staff.GetById.GetByIdStaffResponse;
import com.turkcell.libary_cqrs.application.features.Staff.GetAll.GetAllStaffQuery;
import com.turkcell.libary_cqrs.application.features.Staff.GetAll.GetAllStaffQueryResponse;
import com.turkcell.libary_cqrs.application.features.Auth.Staff.Register.RegisterStaffCommand;
import com.turkcell.libary_cqrs.application.features.Auth.Staff.Login.LoginResponse;
import com.turkcell.libary_cqrs.application.features.Auth.Staff.Login.LoginStaffCommand;
import com.turkcell.libary_cqrs.core.mediator.Mediator;


@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final Mediator mediator;

    public StaffController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @Valid @RequestBody UpdateStaffCommand command) {

        if (!id.equals(command.id())) return ResponseEntity.badRequest().build();
        mediator.send(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        mediator.send(new DeleteStaffCommand(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdStaffResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(mediator.send(new GetByIdStaffQuery(id)));
    }

    @GetMapping
    public ResponseEntity<GetAllStaffQueryResponse> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(mediator.send(new GetAllStaffQuery(page, size)));
    }

    @PostMapping("/register")
    public ResponseEntity<UUID> register(@Valid @RequestBody RegisterStaffCommand command) {

        return ResponseEntity.status(HttpStatus.CREATED).body(mediator.send(command));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginStaffCommand command) {

        return ResponseEntity.ok(mediator.send(command));
    }
}
