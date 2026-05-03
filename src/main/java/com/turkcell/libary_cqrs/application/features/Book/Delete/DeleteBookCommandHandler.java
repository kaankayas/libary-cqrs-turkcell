package com.turkcell.libary_cqrs.application.features.Book.Delete;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Book;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.BookJpaRepository;

@Service
public class DeleteBookCommandHandler implements CommandHandler<DeleteBookCommand, Void> {
    private final BookJpaRepository bookJpaRepository;

    public DeleteBookCommandHandler(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public Void handle(DeleteBookCommand command) {
        Book book = bookJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Kitap bulunamadı"));
        bookJpaRepository.delete(book);
        return null;
    }
}
