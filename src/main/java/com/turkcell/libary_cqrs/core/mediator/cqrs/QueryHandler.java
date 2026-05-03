package com.turkcell.libary_cqrs.core.mediator.cqrs;

public interface QueryHandler<Q extends Query<R>, R> {
   R handle(Q query);
}
