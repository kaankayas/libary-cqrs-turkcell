package com.turkcell.libary_cqrs.application.features.Staff.GetAll;

import java.util.List;

public class GetAllStaffQueryResponse {
    private List<GetAllStaffListItemDto> items;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    public List<GetAllStaffListItemDto> getItems() { return items; }
    public void setItems(List<GetAllStaffListItemDto> items) { this.items = items; }
    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }
    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
    public long getTotalElements() { return totalElements; }
    public void setTotalElements(long totalElements) { this.totalElements = totalElements; }
    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
}
