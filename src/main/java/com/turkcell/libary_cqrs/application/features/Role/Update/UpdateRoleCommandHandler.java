package com.turkcell.libary_cqrs.application.features.Role.Update;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Role;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.RoleJpaRepository;

@Service
public class UpdateRoleCommandHandler implements CommandHandler<UpdateRoleCommand, Void> {
    private final RoleJpaRepository roleJpaRepository;

    public UpdateRoleCommandHandler(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Void handle(UpdateRoleCommand command) {
        Role role = roleJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Rol bulunamadı"));
        role.setName(command.name());
        role.setActive(command.isActive());
        roleJpaRepository.save(role);
        return null;
    }
}
