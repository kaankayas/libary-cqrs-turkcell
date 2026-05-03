package com.turkcell.libary_cqrs.application.features.Role.GetById;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetByIdRoleQuery(UUID id) implements Query<GetByIdRoleResponse> {
}
