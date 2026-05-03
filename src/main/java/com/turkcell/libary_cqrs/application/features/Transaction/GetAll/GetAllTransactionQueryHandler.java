package com.turkcell.libary_cqrs.application.features.Transaction.GetAll;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Transaction;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.TransactionJpaRepository;

@Service
public class GetAllTransactionQueryHandler implements QueryHandler<GetAllTransactionQuery, GetAllTransactionQueryResponse> {
    private final TransactionJpaRepository transactionJpaRepository;

    public GetAllTransactionQueryHandler(TransactionJpaRepository transactionJpaRepository) {
        this.transactionJpaRepository = transactionJpaRepository;
    }

    @Override
    public GetAllTransactionQueryResponse handle(GetAllTransactionQuery query) {
        int pageIndex = query.page() > 0 ? query.page() - 1 : 0;
        Page<Transaction> transactionPage = transactionJpaRepository.findAll(PageRequest.of(pageIndex, query.size()));

        List<GetAllTransactionListItemDto> dtoList = transactionPage.getContent().stream().map(transaction -> {
            GetAllTransactionListItemDto dto = new GetAllTransactionListItemDto();
            dto.setId(transaction.getId());
            dto.setStartDate(transaction.getStartDate());
            dto.setEndDate(transaction.getEndDate());
            dto.setDeliveryDate(transaction.getDeliveryDate());
            dto.setStatus(transaction.getStatus());
            dto.setStudentFullName(transaction.getStudent().getName() + " " + transaction.getStudent().getSurname());
            dto.setBookName(transaction.getBook().getName());
            dto.setStaffFullName(transaction.getStaff().getName() + " " + transaction.getStaff().getSurname());
            return dto;
        }).collect(Collectors.toList());

        GetAllTransactionQueryResponse response = new GetAllTransactionQueryResponse();
        response.setItems(dtoList);
        response.setPageNumber(transactionPage.getNumber() + 1);
        response.setPageSize(transactionPage.getSize());
        response.setTotalElements(transactionPage.getTotalElements());
        response.setTotalPages(transactionPage.getTotalPages());

        return response;
    }
}
