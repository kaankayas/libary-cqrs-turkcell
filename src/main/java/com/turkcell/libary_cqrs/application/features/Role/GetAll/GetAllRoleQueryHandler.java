package com.turkcell.libary_cqrs.application.features.Role.GetAll;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Role;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.RoleJpaRepository;

@Service
public class GetAllRoleQueryHandler implements QueryHandler<GetAllRoleQuery, GetAllRoleQueryResponse> {
    private final RoleJpaRepository roleJpaRepository;

    public GetAllRoleQueryHandler(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public GetAllRoleQueryResponse handle(GetAllRoleQuery query) {
        int pageIndex = query.page() > 0 ? query.page() - 1 : 0;
        Page<Role> rolePage = roleJpaRepository.findAll(PageRequest.of(pageIndex, query.size()));

        List<GetAllRoleListItemDto> dtoList = rolePage.getContent().stream().map(role -> {
            GetAllRoleListItemDto dto = new GetAllRoleListItemDto();
            dto.setId(role.getId());
            dto.setName(role.getName());
            dto.setActive(role.isActive());
            return dto;
        }).collect(Collectors.toList());

        GetAllRoleQueryResponse response = new GetAllRoleQueryResponse();
        response.setItems(dtoList);
        response.setPageNumber(rolePage.getNumber() + 1);
        response.setPageSize(rolePage.getSize());
        response.setTotalElements(rolePage.getTotalElements());
        response.setTotalPages(rolePage.getTotalPages());

        return response;
    }
}
