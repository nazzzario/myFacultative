package com.example.facultative.repo;

import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.CourseStatus;
import com.example.facultative.entity.enums.UserRole;
import com.example.facultative.entity.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllByRole(UserRole userRole);

    List<User> findAllByRoleIn(List<UserRole> roles);

    List<User> findAllByUserStatus(UserStatus userStatus);

    Optional<List<User>> findAllByCoursesId(Long courseId);


}
