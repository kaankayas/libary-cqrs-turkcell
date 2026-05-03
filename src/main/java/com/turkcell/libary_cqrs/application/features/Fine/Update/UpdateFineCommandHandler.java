package com.turkcell.libary_cqrs.application.features.Fine.Update;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Fine;
import com.turkcell.libary_cqrs.domain.entities.Transaction;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.FineJpaRepository;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.TransactionJpaRepository;

@Service
public class UpdateFineCommandHandler implements CommandHandler<UpdateFineCommand, Void> {
    private final FineJpaRepository fineJpaRepository;
    private final TransactionJpaRepository transactionJpaRepository;

    public UpdateFineCommandHandler(
            FineJpaRepository fineJpaRepository,
            TransactionJpaRepository transactionJpaRepository) {
        this.fineJpaRepository = fineJpaRepository;
        this.transactionJpaRepository = transactionJpaRepository;
    }

    @Override
    public Void handle(UpdateFineCommand command) {
        Fine fine = fineJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Ceza bulunamadı"));

        Transaction transaction = transactionJpaRepository.findById(command.transactionId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "İşlem bulunamadı"));

        fine.setPrice(command.price());
        fine.setPaid(command.isPaid());
        fine.setReason(command.reason());
        fine.setTransaction(transaction);

        fineJpaRepository.save(fine);
        return null;
    }
}
