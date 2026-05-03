package com.turkcell.libary_cqrs.application.features.Category.Delete;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Category;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.CategoryJpaRepository;

@Service
public class DeleteCategoryCommandHandler implements CommandHandler<DeleteCategoryCommand, Void> {
    private final CategoryJpaRepository categoryJpaRepository;

    public DeleteCategoryCommandHandler(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Void handle(DeleteCategoryCommand command) {
        Category category = categoryJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Kategori bulunamadı"));
        categoryJpaRepository.delete(category);
        return null;
    }
}
