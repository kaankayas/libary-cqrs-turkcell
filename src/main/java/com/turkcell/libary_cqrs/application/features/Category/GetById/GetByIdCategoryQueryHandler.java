package com.turkcell.libary_cqrs.application.features.Category.GetById;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Category;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.CategoryJpaRepository;

@Service
public class GetByIdCategoryQueryHandler implements QueryHandler<GetByIdCategoryQuery, GetByIdCategoryResponse> {
    private final CategoryJpaRepository categoryJpaRepository;

    public GetByIdCategoryQueryHandler(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public GetByIdCategoryResponse handle(GetByIdCategoryQuery query) {
        Category category = categoryJpaRepository.findById(query.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Kategori bulunamadı"));
        GetByIdCategoryResponse response = new GetByIdCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }
}
