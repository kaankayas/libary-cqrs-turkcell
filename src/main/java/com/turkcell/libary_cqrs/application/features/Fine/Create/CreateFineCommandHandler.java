package com.turkcell.libary_cqrs.application.features.Fine.Create;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Fine;
import com.turkcell.libary_cqrs.domain.entities.Transaction;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.FineJpaRepository;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.TransactionJpaRepository;

@Service
public class CreateFineCommandHandler implements CommandHandler<CreateFineCommand, UUID> {
    private final FineJpaRepository fineJpaRepository;
    private final TransactionJpaRepository transactionJpaRepository;

    public CreateFineCommandHandler(
            FineJpaRepository fineJpaRepository,
            TransactionJpaRepository transactionJpaRepository) {
        this.fineJpaRepository = fineJpaRepository;
        this.transactionJpaRepository = transactionJpaRepository;
    }

    @Override
    public UUID handle(CreateFineCommand command) {
        Transaction transaction = transactionJpaRepository.findById(command.transactionId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "İşlem bulunamadı"));

        Fine fine = new Fine();
        fine.setPrice(command.price());
        fine.setPaid(command.isPaid());
        fine.setReason(command.reason());
        fine.setTransaction(transaction);

        fineJpaRepository.save(fine);
        return fine.getId();
    }
}
