package com.assignment.Controller;

import com.assignment.Repository.UserRepository;
import com.assignment.demo.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // REST endpoint: get all users
    @GetMapping
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // REST endpoint: add user via JSON
    @PostMapping
    @ResponseBody
    public User addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    // Show registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Thymeleaf template
    }

    // Process registration form
// Process registration form
    @PostMapping("/register")
    public String processRegister(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/users/login"; // match your controller mapping
    }
    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Thymeleaf template
    }
}