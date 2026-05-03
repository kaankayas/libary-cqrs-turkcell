package com.turkcell.libary_cqrs.application.features.Transaction.GetById;

import java.util.UUID;
import java.time.LocalDateTime;
import com.turkcell.libary_cqrs.domain.enums.TransactionStatus;

public class GetByIdTransactionResponse {
    private UUID id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime deliveryDate;
    private TransactionStatus status;
    private String studentFullName;
    private String bookName;
    private String staffFullName;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDateTime deliveryDate) { this.deliveryDate = deliveryDate; }
    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }
    public String getStudentFullName() { return studentFullName; }
    public void setStudentFullName(String studentFullName) { this.studentFullName = studentFullName; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getStaffFullName() { return staffFullName; }
    public void setStaffFullName(String staffFullName) { this.staffFullName = staffFullName; }
}
