package com.turkcell.libary_cqrs.application.features.Category.Update;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Category;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.CategoryJpaRepository;

@Service
public class UpdateCategoryCommandHandler implements CommandHandler<UpdateCategoryCommand, Void> {
    private final CategoryJpaRepository categoryJpaRepository;

    public UpdateCategoryCommandHandler(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Void handle(UpdateCategoryCommand command) {
        Category category = categoryJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Kategori bulunamadı"));
        category.setName(command.name());
        categoryJpaRepository.save(category);
        return null;
    }
}
