package com.turkcell.libary_cqrs.infrastructure.persitence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.turkcell.libary_cqrs.domain.entities.Role;

public interface RoleJpaRepository extends JpaRepository<Role, UUID> {
}
