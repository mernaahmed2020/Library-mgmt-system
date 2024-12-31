package com.example.demo.service;

import com.example.demo.model.entity.User;
import com.example.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to find a user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method to find a user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to save a user
    public void saveUser(User user) {
        userRepository.save(user);  // Save the user to the database
    }

    // Check if the entered password matches the user's password
    public boolean checkPassword(User user, String password) {
        if (user != null) {
            return Objects.equals(user.getPassword(), password);  // Comparing plain-text passwords (not secure)
        }
        return false;
    }
}
