package com.turkcell.libary_cqrs.application.features.Author.GetById;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetByIdAuthorQuery(UUID id) implements Query<GetByIdAuthorResponse> {
}
