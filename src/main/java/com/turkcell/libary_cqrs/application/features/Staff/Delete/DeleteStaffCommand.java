package com.turkcell.libary_cqrs.application.features.Staff.Delete;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;

public record DeleteStaffCommand(UUID id) implements Command<Void> {
}
