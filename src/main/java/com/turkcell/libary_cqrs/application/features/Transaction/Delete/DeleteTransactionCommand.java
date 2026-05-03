package com.turkcell.libary_cqrs.application.features.Transaction.Delete;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;

public record DeleteTransactionCommand(UUID id) implements Command<Void> {
}
