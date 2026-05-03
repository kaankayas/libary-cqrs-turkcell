package com.turkcell.libary_cqrs.application.features.Transaction.GetAll;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetAllTransactionQuery(int page, int size) implements Query<GetAllTransactionQueryResponse> {
}
