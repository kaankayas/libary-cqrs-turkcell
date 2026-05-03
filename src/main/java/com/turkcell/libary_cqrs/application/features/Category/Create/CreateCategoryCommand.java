package com.turkcell.libary_cqrs.application.features.Category.Create;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryCommand(
    @NotBlank(message = "Kategori adı boş olamaz")
    @Size(min = 2, max = 30, message = "Kategori adı 2-30 karakter arasında olmalıdır")
    String name
) implements Command<UUID> {
}

