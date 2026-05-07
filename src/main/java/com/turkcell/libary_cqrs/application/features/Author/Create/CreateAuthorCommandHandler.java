package com.turkcell.libary_cqrs.application.features.Author.Create;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Author;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.AuthorJpaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreateAuthorCommandHandler implements CommandHandler<CreateAuthorCommand, UUID> {

    private final AuthorJpaRepository authorJpaRepository;

    public CreateAuthorCommandHandler(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public UUID handle(CreateAuthorCommand command) {
        Author author = new Author();
        author.setName(command.name());
        author.setSurname(command.surname());
        author.setBirthDate(command.birthDate());

        authorJpaRepository.save(author);
        return author.getId();
    }
}
