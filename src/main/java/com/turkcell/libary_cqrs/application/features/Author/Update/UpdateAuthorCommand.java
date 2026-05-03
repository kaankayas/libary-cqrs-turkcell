package com.turkcell.libary_cqrs.application.features.Author.Update;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record UpdateAuthorCommand(
    @NotNull(message = "ID boş olamaz")
    UUID id,

    @NotBlank(message = "İsim boş olamaz")
    @Size(min = 2, max = 50, message = "İsim 2-50 karakter arasında olmalıdır")
    String name,

    @NotBlank(message = "Soyisim boş olamaz")
    @Size(min = 2, max = 50, message = "Soyisim 2-50 karakter arasında olmalıdır")
    String surname,

    @NotNull(message = "Doğum tarihi boş olamaz")
    @Past(message = "Doğum tarihi geçmiş bir tarih olmalıdır")
    java.time.LocalDateTime birthDate
) implements Command<Void> {
}

