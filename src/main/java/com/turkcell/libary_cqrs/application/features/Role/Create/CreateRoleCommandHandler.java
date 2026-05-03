package com.turkcell.libary_cqrs.application.features.Role.Create;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Role;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.RoleJpaRepository;

@Service
public class CreateRoleCommandHandler implements CommandHandler<CreateRoleCommand, UUID> {
    private final RoleJpaRepository roleJpaRepository;

    public CreateRoleCommandHandler(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public UUID handle(CreateRoleCommand command) {
        Role role = new Role();
        role.setName(command.name());
        role.setActive(command.isActive());
        roleJpaRepository.save(role);
        return role.getId();
    }
}
