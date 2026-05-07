package com.turkcell.libary_cqrs.infrastructure.persitence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.turkcell.libary_cqrs.domain.entities.Student;


public interface StudentJpaRepository extends JpaRepository<Student, UUID> {
   Optional<Student> findByEmail(String email);
   Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

   Page<Student> findStudentByFinePointLessThenFive(Pageable pageable);
}