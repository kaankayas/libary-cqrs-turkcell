package com.turkcell.libary_cqrs.application.features.Staff.Update;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Staff;
import com.turkcell.libary_cqrs.domain.entities.Role;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StaffJpaRepository;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.RoleJpaRepository;

@Service
public class UpdateStaffCommandHandler implements CommandHandler<UpdateStaffCommand, Void> {
    private final StaffJpaRepository staffJpaRepository;
    private final RoleJpaRepository roleJpaRepository;

    public UpdateStaffCommandHandler(StaffJpaRepository staffJpaRepository, RoleJpaRepository roleJpaRepository) {
        this.staffJpaRepository = staffJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Void handle(UpdateStaffCommand command) {
        Staff staff = staffJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Personel bulunamadı"));

        Role role = roleJpaRepository.findById(command.roleId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Rol bulunamadı"));

        staff.setName(command.name());
        staff.setSurname(command.surname());
        staff.setEmail(command.email());
        staff.setRole(role);

        staffJpaRepository.save(staff);
        return null;
    }
}
