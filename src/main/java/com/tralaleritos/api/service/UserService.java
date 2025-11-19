package com.tralaleritos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tralaleritos.api.exception.ResourceNotFoundException;
import com.tralaleritos.api.model.User;
import com.tralaleritos.api.repository.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    // CREATE: Save a new User (ID will be generated)
    public User registerNewUser(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    // READ: Retrieve all Users (Existing method)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findActiveUsers() {
        return userRepository.findByActiveTrue();
    }

    // READ: Retrieve a single User by its UUID
    public Optional<User> findUserById(UUID id) {
        return userRepository.findById(id);
    }

    // UPDATE: Update a User with existence check
    public User updateUser(User user) {

        // Checkea q exista el Usero en la db
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            throw new ResourceNotFoundException("User with ID " + user.getId() + " not found. Update failed.");
        }

        // Si existe, actualiza el objecto en la db
        return userRepository.save(user);
    }

    // DELETE: Delete a User by its UUID
    public void deleteUser(UUID id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with ID " + id + " not found. Delete failed.");
        }

        User deactivatedUser = userRepository.findById(id).get();
        deactivatedUser.setActive(false);

        userRepository.save(deactivatedUser);
    }
}
