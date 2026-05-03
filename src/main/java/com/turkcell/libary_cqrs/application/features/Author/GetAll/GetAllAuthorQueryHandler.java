package com.turkcell.libary_cqrs.application.features.Author.GetAll;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Author;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.AuthorJpaRepository;

@Service
public class GetAllAuthorQueryHandler implements QueryHandler<GetAllAuthorQuery, GetAllAuthorQueryResponse> {

    private final AuthorJpaRepository authorJpaRepository;

    public GetAllAuthorQueryHandler(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public GetAllAuthorQueryResponse handle(GetAllAuthorQuery query) {
        int pageIndex = query.page() > 0 ? query.page() - 1 : 0;
        Page<Author> authorPage = authorJpaRepository.findAll(PageRequest.of(pageIndex, query.size()));

        List<GetAllAuthorListItemDto> dtoList = authorPage.getContent().stream().map(author -> {
            GetAllAuthorListItemDto dto = new GetAllAuthorListItemDto();
            dto.setId(author.getId());
            dto.setName(author.getName());
            dto.setSurname(author.getSurname());
            dto.setBirthDate(author.getBirthDate());
            return dto;
        }).collect(Collectors.toList());

        GetAllAuthorQueryResponse response = new GetAllAuthorQueryResponse();
        response.setItems(dtoList);
        response.setPageNumber(authorPage.getNumber() + 1);
        response.setPageSize(authorPage.getSize());
        response.setTotalElements(authorPage.getTotalElements());
        response.setTotalPages(authorPage.getTotalPages());

        return response;
    }
}
