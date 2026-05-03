package com.turkcell.libary_cqrs.application.features.Role.GetById;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Role;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.RoleJpaRepository;

@Service
public class GetByIdRoleQueryHandler implements QueryHandler<GetByIdRoleQuery, GetByIdRoleResponse> {
    private final RoleJpaRepository roleJpaRepository;

    public GetByIdRoleQueryHandler(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public GetByIdRoleResponse handle(GetByIdRoleQuery query) {
        Role role = roleJpaRepository.findById(query.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Rol bulunamadı"));
        GetByIdRoleResponse response = new GetByIdRoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        response.setActive(role.isActive());
        return response;
    }
}
