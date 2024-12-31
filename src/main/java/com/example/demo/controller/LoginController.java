package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // Show the login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Login page
    }

    // Handle login submission
    @PostMapping("/login")
    public String loginUser(String username, String password, Model model) {
        // Find the user by username
        Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isPresent() && userService.checkPassword(optionalUser.get(), password)) {
            // Set user in session (or other user context)
            model.addAttribute("user", optionalUser.get());
            return "redirect:/home";  // Redirect to home page after login
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";  // Return to login page with error message
        }
    }
}
