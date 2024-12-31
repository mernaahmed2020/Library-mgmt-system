package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // Display registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";  // Return the 'registration' template for registration form
    }

    // Handle form submission and register user
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            // Set registration date for the user before saving
            user.setRegistrationDate(java.time.LocalDateTime.now());  // Set current date and time for registration
            userService.saveUser(user);  // Save user to the database
            return "redirect:/user/success";  // Redirect to login page after registration
        } catch (IllegalArgumentException e) {
            // If there's an error, add the error message and return to the registration form
            model.addAttribute("error", e.getMessage());
            return "registration";  // Stay on registration page if there's an error

        }


    }
}
