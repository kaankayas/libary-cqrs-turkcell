package com.turkcell.libary_cqrs.application.features.Role.GetAll;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetAllRoleQuery(int page, int size) implements Query<GetAllRoleQueryResponse> {
}
