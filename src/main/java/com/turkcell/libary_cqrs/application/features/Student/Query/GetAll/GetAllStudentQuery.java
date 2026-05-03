package com.turkcell.libary_cqrs.application.features.Student.Query.GetAll;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Query;

public record GetAllStudentQuery(int page, int size) implements Query<GetAllStudentQueryResponse> {

}
