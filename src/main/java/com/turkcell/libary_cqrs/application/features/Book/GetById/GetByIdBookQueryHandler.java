package com.turkcell.libary_cqrs.application.features.Book.GetById;

import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Book;
import com.turkcell.libary_cqrs.domain.entities.Category;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.BookJpaRepository;

@Service
public class GetByIdBookQueryHandler implements QueryHandler<GetByIdBookQuery, GetByIdBookResponse> {
    private final BookJpaRepository bookJpaRepository;

    public GetByIdBookQueryHandler(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public GetByIdBookResponse handle(GetByIdBookQuery query) {
        Book book = bookJpaRepository.findById(query.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Kitap bulunamadı"));

        GetByIdBookResponse response = new GetByIdBookResponse();
        response.setId(book.getId());
        response.setBarcodeNo(book.getBarcodeNo());
        response.setName(book.getName());
        response.setStock(book.getStock());
        response.setAuthorName(book.getAuthor().getName() + " " + book.getAuthor().getSurname());
        response.setCategoryNames(book.getCategories().stream().map(Category::getName).collect(Collectors.toList()));

        return response;
    }
}
