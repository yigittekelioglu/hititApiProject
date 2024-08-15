package org.example.hititapi.repository;

import org.example.hititapi.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<Repository, Long> {
    Repository findByName(String name);
}
