package com.turkcell.libary_cqrs.application.features.Auth.Student.Login;

import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginCommand(
    @NotBlank(message = "Email alanı boş bırakılamaz")
    @Email(message = "Lütfen geçerli bir email formatı giriniz")
    String email,
    @NotBlank(message = "Şifre alanı boş bırakılamaz")
    String password
) implements Command<String>{

}
