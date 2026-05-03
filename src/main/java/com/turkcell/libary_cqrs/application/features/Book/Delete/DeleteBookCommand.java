package com.turkcell.libary_cqrs.application.features.Book.Delete;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;

public record DeleteBookCommand(UUID id) implements Command<Void> {
}
