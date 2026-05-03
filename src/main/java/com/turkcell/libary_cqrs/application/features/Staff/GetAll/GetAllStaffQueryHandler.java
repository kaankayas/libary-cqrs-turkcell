package com.turkcell.libary_cqrs.application.features.Staff.GetAll;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Staff;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StaffJpaRepository;

@Service
public class GetAllStaffQueryHandler implements QueryHandler<GetAllStaffQuery, GetAllStaffQueryResponse> {
    private final StaffJpaRepository staffJpaRepository;

    public GetAllStaffQueryHandler(StaffJpaRepository staffJpaRepository) {
        this.staffJpaRepository = staffJpaRepository;
    }

    @Override
    public GetAllStaffQueryResponse handle(GetAllStaffQuery query) {
        int pageIndex = query.page() > 0 ? query.page() - 1 : 0;
        Page<Staff> staffPage = staffJpaRepository.findAll(PageRequest.of(pageIndex, query.size()));

        List<GetAllStaffListItemDto> dtoList = staffPage.getContent().stream().map(staff -> {
            GetAllStaffListItemDto dto = new GetAllStaffListItemDto();
            dto.setId(staff.getId());
            dto.setName(staff.getName());
            dto.setSurname(staff.getSurname());
            dto.setEmail(staff.getEmail());
            dto.setRoleName(staff.getRole().getName());
            return dto;
        }).collect(Collectors.toList());

        GetAllStaffQueryResponse response = new GetAllStaffQueryResponse();
        response.setItems(dtoList);
        response.setPageNumber(staffPage.getNumber() + 1);
        response.setPageSize(staffPage.getSize());
        response.setTotalElements(staffPage.getTotalElements());
        response.setTotalPages(staffPage.getTotalPages());

        return response;
    }
}
