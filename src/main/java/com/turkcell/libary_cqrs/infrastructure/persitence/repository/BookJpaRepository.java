package com.turkcell.libary_cqrs.infrastructure.persitence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.turkcell.libary_cqrs.domain.entities.Book;

public interface BookJpaRepository extends JpaRepository<Book, UUID> {
}
