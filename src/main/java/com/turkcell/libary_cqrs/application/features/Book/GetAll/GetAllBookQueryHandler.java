package com.turkcell.libary_cqrs.application.features.Book.GetAll;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Book;
import com.turkcell.libary_cqrs.domain.entities.Category;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.BookJpaRepository;

@Service
public class GetAllBookQueryHandler implements QueryHandler<GetAllBookQuery, GetAllBookQueryResponse> {
    private final BookJpaRepository bookJpaRepository;

    public GetAllBookQueryHandler(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public GetAllBookQueryResponse handle(GetAllBookQuery query) {
        int pageIndex = query.page() > 0 ? query.page() - 1 : 0;
        Page<Book> bookPage = bookJpaRepository.findAll(PageRequest.of(pageIndex, query.size()));

        List<GetAllBookListItemDto> dtoList = bookPage.getContent().stream().map(book -> {
            GetAllBookListItemDto dto = new GetAllBookListItemDto();
            dto.setId(book.getId());
            dto.setBarcodeNo(book.getBarcodeNo());
            dto.setName(book.getName());
            dto.setStock(book.getStock());
            dto.setAuthorName(book.getAuthor().getName() + " " + book.getAuthor().getSurname());
            dto.setCategoryNames(book.getCategories().stream().map(Category::getName).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());

        GetAllBookQueryResponse response = new GetAllBookQueryResponse();
        response.setItems(dtoList);
        response.setPageNumber(bookPage.getNumber() + 1);
        response.setPageSize(bookPage.getSize());
        response.setTotalElements(bookPage.getTotalElements());
        response.setTotalPages(bookPage.getTotalPages());

        return response;
    }
}
