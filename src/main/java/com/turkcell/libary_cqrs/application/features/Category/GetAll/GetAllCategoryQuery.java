package com.turkcell.libary_cqrs.application.features.Category.GetAll;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetAllCategoryQuery(int page, int size) implements Query<GetAllCategoryQueryResponse> {
}
