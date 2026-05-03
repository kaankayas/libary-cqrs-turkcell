package com.turkcell.libary_cqrs.application.features.Student.Query.GetAll;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Student;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StudentJpaRepository;

@Service
public class GetAllStudentQueryHandler implements QueryHandler<GetAllStudentQuery, GetAllStudentQueryResponse> {

    private final StudentJpaRepository studentJpaRepository;

    public GetAllStudentQueryHandler(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public GetAllStudentQueryResponse handle(GetAllStudentQuery query) {
        int pageIndex = query.page() > 0 ? query.page() - 1 : 0;
        
        Page<Student> studentPage = studentJpaRepository
                .findAll(PageRequest.of(pageIndex, query.size()));

        List<GetAllStudentListItemDto> dtoList = studentPage.getContent().stream().map(student -> {
            GetAllStudentListItemDto dto = new GetAllStudentListItemDto();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setSurname(student.getSurname());
            dto.setEmail(student.getEmail());
            return dto;
        }).collect(Collectors.toList());

        GetAllStudentQueryResponse response = new GetAllStudentQueryResponse();
        response.setItems(dtoList);
        response.setPageNumber(studentPage.getNumber() + 1); 
        response.setPageSize(studentPage.getSize());
        response.setTotalElements(studentPage.getTotalElements());
        response.setTotalPages(studentPage.getTotalPages());

        return response;
    }
}
