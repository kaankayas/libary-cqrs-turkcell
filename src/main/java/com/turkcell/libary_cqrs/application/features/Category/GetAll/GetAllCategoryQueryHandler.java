package com.turkcell.libary_cqrs.application.features.Category.GetAll;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Category;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.CategoryJpaRepository;

@Service
public class GetAllCategoryQueryHandler implements QueryHandler<GetAllCategoryQuery, GetAllCategoryQueryResponse> {
    private final CategoryJpaRepository categoryJpaRepository;

    public GetAllCategoryQueryHandler(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public GetAllCategoryQueryResponse handle(GetAllCategoryQuery query) {
        int pageIndex = query.page() > 0 ? query.page() - 1 : 0;
        Page<Category> categoryPage = categoryJpaRepository.findAll(PageRequest.of(pageIndex, query.size()));

        List<GetAllCategoryListItemDto> dtoList = categoryPage.getContent().stream().map(category -> {
            GetAllCategoryListItemDto dto = new GetAllCategoryListItemDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            return dto;
        }).collect(Collectors.toList());

        GetAllCategoryQueryResponse response = new GetAllCategoryQueryResponse();
        response.setItems(dtoList);
        response.setPageNumber(categoryPage.getNumber() + 1);
        response.setPageSize(categoryPage.getSize());
        response.setTotalElements(categoryPage.getTotalElements());
        response.setTotalPages(categoryPage.getTotalPages());

        return response;
    }
}
