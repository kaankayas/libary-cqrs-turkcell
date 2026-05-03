package com.turkcell.libary_cqrs.application.features.Transaction.Create;

import java.util.UUID;
import java.time.LocalDateTime;
import com.turkcell.libary_cqrs.domain.enums.TransactionStatus;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotNull;

public record CreateTransactionCommand(
    @NotNull(message = "Başlangıç tarihi boş olamaz")
    LocalDateTime startDate,

    @NotNull(message = "Bitiş tarihi boş olamaz")
    LocalDateTime endDate,

    @NotNull(message = "Durum boş olamaz")
    TransactionStatus status,

    @NotNull(message = "Öğrenci ID boş olamaz")
    UUID studentId,

    @NotNull(message = "Kitap ID boş olamaz")
    UUID bookId,

    @NotNull(message = "Personel ID boş olamaz")
    UUID staffId
) implements Command<UUID> {
}

