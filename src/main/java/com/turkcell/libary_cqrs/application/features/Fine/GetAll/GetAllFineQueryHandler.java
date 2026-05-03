package com.turkcell.libary_cqrs.application.features.Fine.GetAll;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Fine;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.FineJpaRepository;

@Service
public class GetAllFineQueryHandler implements QueryHandler<GetAllFineQuery, GetAllFineQueryResponse> {
    private final FineJpaRepository fineJpaRepository;

    public GetAllFineQueryHandler(FineJpaRepository fineJpaRepository) {
        this.fineJpaRepository = fineJpaRepository;
    }

    @Override
    public GetAllFineQueryResponse handle(GetAllFineQuery query) {
        int pageIndex = query.page() > 0 ? query.page() - 1 : 0;
        Page<Fine> finePage = fineJpaRepository.findAll(PageRequest.of(pageIndex, query.size()));

        List<GetAllFineListItemDto> dtoList = finePage.getContent().stream().map(fine -> {
            GetAllFineListItemDto dto = new GetAllFineListItemDto();
            dto.setId(fine.getId());
            dto.setPrice(fine.getPrice());
            dto.setPaid(fine.isPaid());
            dto.setReason(fine.getReason());
            dto.setTransactionId(fine.getTransaction().getId());
            return dto;
        }).collect(Collectors.toList());

        GetAllFineQueryResponse response = new GetAllFineQueryResponse();
        response.setItems(dtoList);
        response.setPageNumber(finePage.getNumber() + 1);
        response.setPageSize(finePage.getSize());
        response.setTotalElements(finePage.getTotalElements());
        response.setTotalPages(finePage.getTotalPages());

        return response;
    }
}
