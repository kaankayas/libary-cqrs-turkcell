package com.turkcell.libary_cqrs.application.features.Category.GetById;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetByIdCategoryQuery(UUID id) implements Query<GetByIdCategoryResponse> {
}
