package com.example.facultative.repo;

import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    List<User> findAllByRole(UserRole userRole);

}
