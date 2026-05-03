package com.turkcell.libary_cqrs.application.features.Author.Delete;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Author;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.AuthorJpaRepository;

@Service
public class DeleteAuthorCommandHandler implements CommandHandler<DeleteAuthorCommand, Void> {

    private final AuthorJpaRepository authorJpaRepository;

    public DeleteAuthorCommandHandler(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public Void handle(DeleteAuthorCommand command) {
        Author author = authorJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Yazar bulunamadı"));

        authorJpaRepository.delete(author);
        return null;
    }
}
