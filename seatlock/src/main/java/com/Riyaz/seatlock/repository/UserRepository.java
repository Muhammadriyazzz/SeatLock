package com.Riyaz.seatlock.repository;

import com.Riyaz.seatlock.entity.User;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, Id> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
