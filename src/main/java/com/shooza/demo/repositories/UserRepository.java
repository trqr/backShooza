package com.shooza.demo.repositories;

import com.shooza.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    Optional<User> findOptionalByEmail(String email);

    boolean existsByEmail(String email);
}
