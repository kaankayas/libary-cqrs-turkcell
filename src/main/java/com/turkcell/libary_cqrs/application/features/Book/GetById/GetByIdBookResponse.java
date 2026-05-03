package com.turkcell.libary_cqrs.application.features.Book.GetById;

import java.util.UUID;
import java.util.List;

public class GetByIdBookResponse {
    private UUID id;
    private String barcodeNo;
    private String name;
    private int stock;
    private String authorName;
    private List<String> categoryNames;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getBarcodeNo() { return barcodeNo; }
    public void setBarcodeNo(String barcodeNo) { this.barcodeNo = barcodeNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public List<String> getCategoryNames() { return categoryNames; }
    public void setCategoryNames(List<String> categoryNames) { this.categoryNames = categoryNames; }
}
