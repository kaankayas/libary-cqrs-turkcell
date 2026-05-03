package com.turkcell.libary_cqrs.infrastructure.persitence.repository;

import java.util.UUID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.turkcell.libary_cqrs.domain.entities.Staff;

public interface StaffJpaRepository extends JpaRepository<Staff, UUID> {
    Optional<Staff> findByEmail(String email);
}
