package com.turkcell.libary_cqrs.application.features.Category.Delete;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;

public record DeleteCategoryCommand(UUID id) implements Command<Void> {
}
