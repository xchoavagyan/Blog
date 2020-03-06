package com.example.demo.conroller;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> find(@PathVariable(value = "id") int userId) {
        User user = userService.findByID(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> find() {
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @PutMapping(value = "user/{id}")
    public void update(@PathVariable(value = "id") int userId, @RequestBody User user) {
        userService.update(userId, user);
    }

    @DeleteMapping(value = "user/{id}")
    public void delete(@PathVariable(value = "id") int userId) {
        userService.delete(userId);
    }
}
