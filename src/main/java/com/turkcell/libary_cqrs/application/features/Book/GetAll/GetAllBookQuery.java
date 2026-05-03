package com.turkcell.libary_cqrs.application.features.Book.GetAll;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetAllBookQuery(int page, int size) implements Query<GetAllBookQueryResponse> {
}
