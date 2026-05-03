package com.turkcell.libary_cqrs.application.features.Transaction.Delete;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Transaction;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.TransactionJpaRepository;

@Service
public class DeleteTransactionCommandHandler implements CommandHandler<DeleteTransactionCommand, Void> {
    private final TransactionJpaRepository transactionJpaRepository;

    public DeleteTransactionCommandHandler(TransactionJpaRepository transactionJpaRepository) {
        this.transactionJpaRepository = transactionJpaRepository;
    }

    @Override
    public Void handle(DeleteTransactionCommand command) {
        Transaction transaction = transactionJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "İşlem bulunamadı"));
        transactionJpaRepository.delete(transaction);
        return null;
    }
}
