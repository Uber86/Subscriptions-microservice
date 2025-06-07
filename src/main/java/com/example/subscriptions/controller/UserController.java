package com.example.subscriptions.controller;

import com.example.subscriptions.model.User;
import com.example.subscriptions.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(service.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(service.getUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(userId, user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long userId) {
        service.deleteUser(userId);
    }
}
