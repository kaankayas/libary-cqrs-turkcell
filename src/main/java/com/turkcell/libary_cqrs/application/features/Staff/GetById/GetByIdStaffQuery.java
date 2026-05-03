package com.turkcell.libary_cqrs.application.features.Staff.GetById;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetByIdStaffQuery(UUID id) implements Query<GetByIdStaffResponse> {
}
