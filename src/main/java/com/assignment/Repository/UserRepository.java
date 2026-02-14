package com.assignment.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment.demo.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}