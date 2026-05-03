package com.turkcell.libary_cqrs.application.features.Fine.Create;

import java.util.UUID;
import java.math.BigDecimal;
import com.turkcell.libary_cqrs.domain.enums.FineReason;
import com.turkcell.libary_cqrs.core.mediator.cqrs.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateFineCommand(
    @NotNull(message = "Fiyat boş olamaz")
    @Positive(message = "Fiyat pozitif olmalıdır")
    BigDecimal price,

    boolean isPaid,

    @NotNull(message = "Ceza nedeni boş olamaz")
    FineReason reason,

    @NotNull(message = "İşlem ID boş olamaz")
    UUID transactionId
) implements Command<UUID> {
}

