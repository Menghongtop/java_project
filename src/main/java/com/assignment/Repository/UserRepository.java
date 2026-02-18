package com.assignment.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.assignment.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}