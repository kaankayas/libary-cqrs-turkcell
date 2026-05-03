package com.turkcell.libary_cqrs.application.features.Student.Query.Search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Student;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StudentJpaRepository;

@Service
public class SearchByNameQueryHandler implements QueryHandler<SearchByNameQuery, SearchByNameQueryResponse> {

    private final StudentJpaRepository studentJpaRepository;

    public SearchByNameQueryHandler(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public SearchByNameQueryResponse handle(SearchByNameQuery query) {
        int pageIndex = query.page() > 0 ? query.page() - 1 : 0;
        
        Page<Student> studentPage;
        if (query.search() == null || query.search().isBlank()) {
            studentPage = studentJpaRepository.findAll(PageRequest.of(pageIndex, query.size()));
        }
        else {
            studentPage = studentJpaRepository.findByNameContainingIgnoreCase(query.search(), PageRequest.of(pageIndex, query.size()));
        }

        List<SearchByNameListItemDto> dtoList = studentPage.getContent().stream().map(student -> {
            SearchByNameListItemDto dto = new SearchByNameListItemDto();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setSurname(student.getSurname());
            dto.setEmail(student.getEmail());
            return dto;
        }).collect(Collectors.toList());

        SearchByNameQueryResponse response = new SearchByNameQueryResponse();
        response.setItems(dtoList);
        response.setPageNumber(studentPage.getNumber() + 1);
        response.setPageSize(studentPage.getSize());
        response.setTotalElements(studentPage.getTotalElements());
        response.setTotalPages(studentPage.getTotalPages());

        return response;
    }

}
