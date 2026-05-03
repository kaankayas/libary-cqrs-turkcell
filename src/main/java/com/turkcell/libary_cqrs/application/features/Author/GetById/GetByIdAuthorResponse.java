package com.turkcell.libary_cqrs.application.features.Author.GetById;

import java.util.UUID;
import java.time.LocalDateTime;

public class GetByIdAuthorResponse {
    private UUID id;
    private String name;
    private String surname;
    private LocalDateTime birthDate;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public LocalDateTime getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDateTime birthDate) { this.birthDate = birthDate; }
}
