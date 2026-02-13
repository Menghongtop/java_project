package com.assignment.Controller;

import com.assignment.Repository.UserRepository;
import com.assignment.demo.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();   // works because UserRepository extends JpaRepository
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return repo.save(user);
    }
}