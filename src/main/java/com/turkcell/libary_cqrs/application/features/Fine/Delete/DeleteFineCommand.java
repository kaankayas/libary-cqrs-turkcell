package com.turkcell.libary_cqrs.application.features.Fine.Delete;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;

public record DeleteFineCommand(UUID id) implements Command<Void> {
}
