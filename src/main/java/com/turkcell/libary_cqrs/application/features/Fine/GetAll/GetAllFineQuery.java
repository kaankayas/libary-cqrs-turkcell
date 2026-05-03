package com.turkcell.libary_cqrs.application.features.Fine.GetAll;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetAllFineQuery(int page, int size) implements Query<GetAllFineQueryResponse> {
}
