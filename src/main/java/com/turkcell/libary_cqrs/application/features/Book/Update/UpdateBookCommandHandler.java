package com.turkcell.libary_cqrs.application.features.Book.Update;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Book;
import com.turkcell.libary_cqrs.domain.entities.Author;
import com.turkcell.libary_cqrs.domain.entities.Category;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.BookJpaRepository;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.AuthorJpaRepository;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.CategoryJpaRepository;

@Service
public class UpdateBookCommandHandler implements CommandHandler<UpdateBookCommand, Void> {
    private final BookJpaRepository bookJpaRepository;
    private final AuthorJpaRepository authorJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;

    public UpdateBookCommandHandler(
            BookJpaRepository bookJpaRepository,
            AuthorJpaRepository authorJpaRepository,
            CategoryJpaRepository categoryJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
        this.authorJpaRepository = authorJpaRepository;
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Void handle(UpdateBookCommand command) {
        Book book = bookJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Kitap bulunamadı"));

        Author author = authorJpaRepository.findById(command.authorId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Yazar bulunamadı"));

        List<Category> categories = categoryJpaRepository.findAllById(command.categoryIds());

        book.setBarcodeNo(command.barcodeNo());
        book.setName(command.name());
        book.setStock(command.stock());
        book.setAuthor(author);
        book.setCategories(categories);

        bookJpaRepository.save(book);
        return null;
    }
}
