package com.turkcell.libary_cqrs.application.features.Category.Create;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Category;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.CategoryJpaRepository;

@Service
public class CreateCategoryCommandHandler implements CommandHandler<CreateCategoryCommand, UUID> {
    private final CategoryJpaRepository categoryJpaRepository;

    public CreateCategoryCommandHandler(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public UUID handle(CreateCategoryCommand command) {
        Category category = new Category();
        category.setName(command.name());
        categoryJpaRepository.save(category);
        return category.getId();
    }
}
