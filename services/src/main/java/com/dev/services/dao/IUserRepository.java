package com.dev.services.dao;

import com.dev.services.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
