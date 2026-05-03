package com.turkcell.libary_cqrs.application.features.Staff.Delete;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Staff;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StaffJpaRepository;

@Service
public class DeleteStaffCommandHandler implements CommandHandler<DeleteStaffCommand, Void> {
    private final StaffJpaRepository staffJpaRepository;

    public DeleteStaffCommandHandler(StaffJpaRepository staffJpaRepository) {
        this.staffJpaRepository = staffJpaRepository;
    }

    @Override
    public Void handle(DeleteStaffCommand command) {
        Staff staff = staffJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Personel bulunamadı"));
        staffJpaRepository.delete(staff);
        return null;
    }
}
