package com.turkcell.libary_cqrs.application.features.Staff.Update;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateStaffCommand(
    @NotNull(message = "ID boş olamaz")
    UUID id,

    @NotBlank(message = "İsim boş olamaz")
    String name,

    @NotBlank(message = "Soyisim boş olamaz")
    String surname,

    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçersiz email formatı")
    String email,

    @NotNull(message = "Rol ID boş olamaz")
    UUID roleId
) implements Command<Void> {
}

