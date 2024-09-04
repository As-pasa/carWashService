package com.asPasa.testTask.repositories;

import com.asPasa.testTask.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    Optional<User> findByName(String name);
}
