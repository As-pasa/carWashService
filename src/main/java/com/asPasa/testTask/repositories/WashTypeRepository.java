package com.asPasa.testTask.repositories;

import com.asPasa.testTask.models.WashType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WashTypeRepository extends JpaRepository<WashType,Long> {
    Optional<WashType> findByName(String name);
    boolean existsByName(String name);
}
