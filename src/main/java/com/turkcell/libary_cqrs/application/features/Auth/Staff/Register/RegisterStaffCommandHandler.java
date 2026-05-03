package com.turkcell.libary_cqrs.application.features.Auth.Staff.Register;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Staff;
import com.turkcell.libary_cqrs.domain.entities.Role;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StaffJpaRepository;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.RoleJpaRepository;

@Service
public class RegisterStaffCommandHandler implements CommandHandler<RegisterStaffCommand, UUID> {
    private final StaffJpaRepository staffJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterStaffCommandHandler(
            StaffJpaRepository staffJpaRepository,
            RoleJpaRepository roleJpaRepository,
            PasswordEncoder passwordEncoder) {
        this.staffJpaRepository = staffJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UUID handle(RegisterStaffCommand command) {
        if (staffJpaRepository.findByEmail(command.email()).isPresent()) {
            throw new BusinessException(HttpStatus.CONFLICT, "Bu email adresi zaten kullanımda.");
        }

        Role role = roleJpaRepository.findById(command.roleId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Rol bulunamadı"));

        Staff staff = new Staff();
        staff.setName(command.name());
        staff.setSurname(command.surname());
        staff.setEmail(command.email());
        staff.setPassword(passwordEncoder.encode(command.password()));
        staff.setRole(role);

        staffJpaRepository.save(staff);
        return staff.getId();
    }
}
