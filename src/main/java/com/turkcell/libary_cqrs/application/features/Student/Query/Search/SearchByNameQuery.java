package com.turkcell.libary_cqrs.application.features.Student.Query.Search;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record SearchByNameQuery(String search, int page, int size) implements Query<SearchByNameQueryResponse> {

}
