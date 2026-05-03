package com.turkcell.libary_cqrs.application.features.Auth.Student.Register;

import java.util.UUID;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterCommand(
  @NotBlank(message = "İsim boş olamaz")
  String name,

  @NotBlank(message = "Soyisim boş olamaz")
  String surname,

  @NotBlank(message = "Email boş olamaz")
  @Email(message = "Geçersiz email formatı")
  String email,

  @NotBlank(message = "Şifre boş olamaz")
  @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır")
  String password
) implements Command<UUID>{}
