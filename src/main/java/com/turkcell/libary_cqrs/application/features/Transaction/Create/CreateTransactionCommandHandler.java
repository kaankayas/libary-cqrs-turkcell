package com.turkcell.libary_cqrs.application.features.Transaction.Create;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.turkcell.libary_cqrs.core.exceptions.type.BusinessException;
import com.turkcell.libary_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.libary_cqrs.domain.entities.Transaction;
import com.turkcell.libary_cqrs.domain.entities.Student;
import com.turkcell.libary_cqrs.domain.entities.Book;
import com.turkcell.libary_cqrs.domain.entities.Staff;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.TransactionJpaRepository;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StudentJpaRepository;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.BookJpaRepository;
import com.turkcell.libary_cqrs.infrastructure.persitence.repository.StaffJpaRepository;

@Service
public class CreateTransactionCommandHandler implements CommandHandler<CreateTransactionCommand, UUID> {
    private final TransactionJpaRepository transactionJpaRepository;
    private final StudentJpaRepository studentJpaRepository;
    private final BookJpaRepository bookJpaRepository;
    private final StaffJpaRepository staffJpaRepository;

    public CreateTransactionCommandHandler(
            TransactionJpaRepository transactionJpaRepository,
            StudentJpaRepository studentJpaRepository,
            BookJpaRepository bookJpaRepository,
            StaffJpaRepository staffJpaRepository) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.studentJpaRepository = studentJpaRepository;
        this.bookJpaRepository = bookJpaRepository;
        this.staffJpaRepository = staffJpaRepository;
    }

    @Override
    public UUID handle(CreateTransactionCommand command) {
        Student student = studentJpaRepository.findById(command.studentId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Öğrenci bulunamadı"));
        Book book = bookJpaRepository.findById(command.bookId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Kitap bulunamadı"));
        Staff staff = staffJpaRepository.findById(command.staffId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Personel bulunamadı"));

        Transaction transaction = new Transaction();
        transaction.setStartDate(command.startDate());
        transaction.setEndDate(command.endDate());
        transaction.setStatus(command.status());
        transaction.setStudent(student);
        transaction.setBook(book);
        transaction.setStaff(staff);

        transactionJpaRepository.save(transaction);
        return transaction.getId();
    }
}
