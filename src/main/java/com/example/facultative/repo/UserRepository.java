package com.example.facultative.repo;

import com.example.facultative.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);
}
