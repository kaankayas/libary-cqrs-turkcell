package com.turkcell.libary_cqrs.application.features.Fine.GetById;

import java.util.UUID;
import java.math.BigDecimal;
import com.turkcell.libary_cqrs.domain.enums.FineReason;

public class GetByIdFineResponse {
    private UUID id;
    private BigDecimal price;
    private boolean isPaid;
    private FineReason reason;
    private UUID transactionId;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean isPaid) { this.isPaid = isPaid; }
    public FineReason getReason() { return reason; }
    public void setReason(FineReason reason) { this.reason = reason; }
    public UUID getTransactionId() { return transactionId; }
    public void setTransactionId(UUID transactionId) { this.transactionId = transactionId; }
}
