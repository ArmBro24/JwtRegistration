package com.example.javaregistration.repository;

import com.example.javaregistration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsById(Long id);

    boolean existsByEmail(String email);
}
