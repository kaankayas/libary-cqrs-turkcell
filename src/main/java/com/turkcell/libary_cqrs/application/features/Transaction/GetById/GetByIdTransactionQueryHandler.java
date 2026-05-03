package com.turkcell.libary_cqrs.application.features.Transaction.GetById;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.libary_cqrs.domain.entities.Transaction;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.TransactionJpaRepository;

@Service
public class GetByIdTransactionQueryHandler implements QueryHandler<GetByIdTransactionQuery, GetByIdTransactionResponse> {
    private final TransactionJpaRepository transactionJpaRepository;

    public GetByIdTransactionQueryHandler(TransactionJpaRepository transactionJpaRepository) {
        this.transactionJpaRepository = transactionJpaRepository;
    }

    @Override
    public GetByIdTransactionResponse handle(GetByIdTransactionQuery query) {
        Transaction transaction = transactionJpaRepository.findById(query.id())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "İşlem bulunamadı"));

        GetByIdTransactionResponse response = new GetByIdTransactionResponse();
        response.setId(transaction.getId());
        response.setStartDate(transaction.getStartDate());
        response.setEndDate(transaction.getEndDate());
        response.setDeliveryDate(transaction.getDeliveryDate());
        response.setStatus(transaction.getStatus());
        response.setStudentFullName(transaction.getStudent().getName() + " " + transaction.getStudent().getSurname());
        response.setBookName(transaction.getBook().getName());
        response.setStaffFullName(transaction.getStaff().getName() + " " + transaction.getStaff().getSurname());

        return response;
    }
}
