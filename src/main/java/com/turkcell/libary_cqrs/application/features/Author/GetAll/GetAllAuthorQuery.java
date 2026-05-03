package com.turkcell.libary_cqrs.application.features.Author.GetAll;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetAllAuthorQuery(int page, int size) implements Query<GetAllAuthorQueryResponse> {
}
