package com.tralaleritos.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tralaleritos.api.DTO.StoredUser;
import com.tralaleritos.api.model.User;
import com.tralaleritos.api.service.UserService;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAllUsers();

        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active")
    public ResponseEntity<List<User>> getActiveUsers() {
        List<User> users = userService.findActiveUsers();

        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> userOptional = userService.findUserById(id);

        if (userOptional.isPresent()) {

            User user = userOptional.get();
            return new ResponseEntity<>(user, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User savedUser = userService.registerNewUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User userDetails) {

        if (!id.equals(userDetails.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User updatedUser = userService.updateUser(userDetails);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //esto es la conexion del login a la api desde el userservice que hiciste
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = body.get("password");
        //busca por el email en la base de datos
        Optional<User> optionalUser = userService.findByEmail(email);
        //si no lo encuentra, devuelve un error
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
        }
        //si lo encuentra, verifica la contraseña
        User user = optionalUser.get();

        //si la contraseña es incorrecta, devuelve un error 
        if (!userService.isPasswordCorrect(user, password)) {
            return ResponseEntity.status(401).body("Correo o contraseña incorrectos");
        }

        StoredUser resp = new StoredUser(
                user.getId(),
                user.getName(),
                user.getLast_name(),
                user.getEmail(),
                user.getRole(),
                user.isDuoc());

        return ResponseEntity.ok(resp);
    }

}
