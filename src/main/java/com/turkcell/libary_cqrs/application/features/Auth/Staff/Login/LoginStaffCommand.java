package com.turkcell.libary_cqrs.application.features.Auth.Staff.Login;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginStaffCommand(
    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçersiz email formatı")
    String email,

    @NotBlank(message = "Şifre boş olamaz")
    String password
) implements Command<String> {
}

