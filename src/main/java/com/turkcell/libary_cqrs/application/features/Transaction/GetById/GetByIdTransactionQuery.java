package com.turkcell.libary_cqrs.application.features.Transaction.GetById;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetByIdTransactionQuery(UUID id) implements Query<GetByIdTransactionResponse> {
}
