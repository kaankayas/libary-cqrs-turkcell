package com.turkcell.libary_cqrs.application.features.Book.Create;

import java.util.UUID;
import java.util.List;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateBookCommand(
    @NotBlank(message = "Barkod numarası boş olamaz")
    String barcodeNo,

    @NotBlank(message = "Kitap adı boş olamaz")
    String name,

    @Min(value = 0, message = "Stok adedi negatif olamaz")
    int stock,

    @NotNull(message = "Yazar ID boş olamaz")
    UUID authorId,

    @NotEmpty(message = "En az bir kategori seçilmelidir")
    List<UUID> categoryIds
) implements Command<UUID> {
}

