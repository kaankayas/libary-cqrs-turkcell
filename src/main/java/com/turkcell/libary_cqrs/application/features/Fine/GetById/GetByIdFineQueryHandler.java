package com.turkcell.libary_cqrs.application.features.Fine.GetById;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Fine;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.FineJpaRepository;

@Service
public class GetByIdFineQueryHandler implements QueryHandler<GetByIdFineQuery, GetByIdFineResponse> {
    private final FineJpaRepository fineJpaRepository;

    public GetByIdFineQueryHandler(FineJpaRepository fineJpaRepository) {
        this.fineJpaRepository = fineJpaRepository;
    }

    @Override
    public GetByIdFineResponse handle(GetByIdFineQuery query) {
        Fine fine = fineJpaRepository.findById(query.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Ceza bulunamadı"));

        GetByIdFineResponse response = new GetByIdFineResponse();
        response.setId(fine.getId());
        response.setPrice(fine.getPrice());
        response.setPaid(fine.isPaid());
        response.setReason(fine.getReason());
        response.setTransactionId(fine.getTransaction().getId());

        return response;
    }
}
