package com.turkcell.libary_cqrs.application.features.Role.Create;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateRoleCommand(
    @NotBlank(message = "Rol adı boş olamaz")
    String name,

    boolean isActive
) implements Command<UUID> {
}

