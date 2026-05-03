package com.turkcell.libary_cqrs.application.features.Book.GetById;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetByIdBookQuery(UUID id) implements Query<GetByIdBookResponse> {
}
