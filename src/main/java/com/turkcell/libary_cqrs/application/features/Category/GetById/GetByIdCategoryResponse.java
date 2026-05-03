package com.turkcell.libary_cqrs.application.features.Category.GetById;

import java.util.UUID;

public class GetByIdCategoryResponse {
    private UUID id;
    private String name;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
