package com.turkcell.libary_cqrs.application.features.Staff.GetAll;

import java.util.UUID;

public class GetAllStaffListItemDto {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String roleName;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
}
