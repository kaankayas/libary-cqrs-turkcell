package com.turkcell.libary_cqrs.application.features.Staff.GetAll;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetAllStaffQuery(int page, int size) implements Query<GetAllStaffQueryResponse> {
}
