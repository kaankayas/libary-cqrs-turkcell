package com.turkcell.libary_cqrs.application.features.Role.Delete;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Role;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.RoleJpaRepository;

@Service
public class DeleteRoleCommandHandler implements CommandHandler<DeleteRoleCommand, Void> {
    private final RoleJpaRepository roleJpaRepository;

    public DeleteRoleCommandHandler(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public Void handle(DeleteRoleCommand command) {
        Role role = roleJpaRepository.findById(command.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Rol bulunamadı"));
        roleJpaRepository.delete(role);
        return null;
    }
}
