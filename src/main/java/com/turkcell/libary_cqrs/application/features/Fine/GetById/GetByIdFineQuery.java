package com.turkcell.libary_cqrs.application.features.Fine.GetById;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetByIdFineQuery(UUID id) implements Query<GetByIdFineResponse> {
}
