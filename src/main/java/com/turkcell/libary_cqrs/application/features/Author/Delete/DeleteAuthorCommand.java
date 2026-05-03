package com.turkcell.libary_cqrs.application.features.Author.Delete;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;

public record DeleteAuthorCommand(UUID id) implements Command<Void> {
}
