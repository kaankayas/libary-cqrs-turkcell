package com.turkcell.libary_cqrs.application.features.Author.Update;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Author;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.AuthorJpaRepository;

@Service
public class UpdateAuthorCommandHandler implements CommandHandler<UpdateAuthorCommand, Void> {

    private final AuthorJpaRepository authorJpaRepository;

    public UpdateAuthorCommandHandler(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public Void handle(UpdateAuthorCommand command) {
        Author author = authorJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Yazar bulunamadı"));

        author.setName(command.name());
        author.setSurname(command.surname());
        author.setBirthDate(command.birthDate());

        authorJpaRepository.save(author);
        return null;
    }
}
