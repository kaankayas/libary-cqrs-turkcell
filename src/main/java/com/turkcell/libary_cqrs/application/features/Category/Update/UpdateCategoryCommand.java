package com.turkcell.libary_cqrs.application.features.Category.Update;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateCategoryCommand(
    @NotNull(message = "ID boş olamaz")
    UUID id,

    @NotBlank(message = "Kategori adı boş olamaz")
    @Size(min = 2, max = 30, message = "Kategori adı 2-30 karakter arasında olmalıdır")
    String name
) implements Command<Void> {
}

