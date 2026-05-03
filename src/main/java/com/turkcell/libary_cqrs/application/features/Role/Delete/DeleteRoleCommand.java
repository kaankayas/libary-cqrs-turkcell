package com.turkcell.libary_cqrs.application.features.Role.Delete;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;

public record DeleteRoleCommand(UUID id) implements Command<Void> {
}
