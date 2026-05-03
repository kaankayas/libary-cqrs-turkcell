package com.turkcell.libary_cqrs.application.features.Role.Update;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateRoleCommand(
    @NotNull(message = "ID boş olamaz")
    UUID id,

    @NotBlank(message = "Rol adı boş olamaz")
    String name,

    boolean isActive
) implements Command<Void> {
}

