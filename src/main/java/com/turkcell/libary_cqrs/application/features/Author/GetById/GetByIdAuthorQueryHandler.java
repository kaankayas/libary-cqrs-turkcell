package com.turkcell.libary_cqrs.application.features.Author.GetById;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Author;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.AuthorJpaRepository;

@Service
public class GetByIdAuthorQueryHandler implements QueryHandler<GetByIdAuthorQuery, GetByIdAuthorResponse> {

    private final AuthorJpaRepository authorJpaRepository;

    public GetByIdAuthorQueryHandler(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public GetByIdAuthorResponse handle(GetByIdAuthorQuery query) {
        Author author = authorJpaRepository.findById(query.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Yazar bulunamadı"));

        GetByIdAuthorResponse response = new GetByIdAuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setSurname(author.getSurname());
        response.setBirthDate(author.getBirthDate());

        return response;
    }
}
