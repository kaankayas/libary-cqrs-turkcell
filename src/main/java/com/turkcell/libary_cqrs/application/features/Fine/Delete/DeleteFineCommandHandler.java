package com.turkcell.libary_cqrs.application.features.Fine.Delete;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Fine;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.FineJpaRepository;

@Service
public class DeleteFineCommandHandler implements CommandHandler<DeleteFineCommand, Void> {
    private final FineJpaRepository fineJpaRepository;

    public DeleteFineCommandHandler(FineJpaRepository fineJpaRepository) {
        this.fineJpaRepository = fineJpaRepository;
    }

    @Override
    public Void handle(DeleteFineCommand command) {
        Fine fine = fineJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Ceza bulunamadı"));
        fineJpaRepository.delete(fine);
        return null;
    }
}
