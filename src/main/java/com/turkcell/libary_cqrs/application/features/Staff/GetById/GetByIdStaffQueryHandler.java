package com.turkcell.libary_cqrs.application.features.Staff.GetById;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Staff;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StaffJpaRepository;

@Service
public class GetByIdStaffQueryHandler implements QueryHandler<GetByIdStaffQuery, GetByIdStaffResponse> {
    private final StaffJpaRepository staffJpaRepository;

    public GetByIdStaffQueryHandler(StaffJpaRepository staffJpaRepository) {
        this.staffJpaRepository = staffJpaRepository;
    }

    @Override
    public GetByIdStaffResponse handle(GetByIdStaffQuery query) {
        Staff staff = staffJpaRepository.findById(query.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Personel bulunamadı"));

        GetByIdStaffResponse response = new GetByIdStaffResponse();
        response.setId(staff.getId());
        response.setName(staff.getName());
        response.setSurname(staff.getSurname());
        response.setEmail(staff.getEmail());
        response.setRoleName(staff.getRole().getName());

        return response;
    }
}
