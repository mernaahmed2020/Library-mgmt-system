package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Display the registration success page
    @GetMapping("/success")
    public String registrationSuccess() {
        return "registration-success";  // Registration success page
    }

    // Show the registration form
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // Registration page
    }

    // Handle user registration submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Check if the username or email already exists
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";  // Return to the registration page with error message
        }
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already exists");
            return "register";  // Return to the registration page with error message
        }

        // Save the new user to the database
        userService.saveUser(user);
        return "redirect:/user/success";  // Redirect to success page after registration
    }
}
