package com.questapp.controller;

import com.questapp.dto.request.UserCreateRequest;
import com.questapp.model.User;
import com.questapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserCreateRequest user){
        userService.saveUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("message", "User created succesfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> updateUser(@RequestParam("id") Long id, @RequestBody User user){
        userService.updateUser(id, user);
        Map<String, String> map = new HashMap<>();
        map.put("message", "User created succesfully");
        map.put("status", "true");
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        String message = "User is successfully deleted";
        return ResponseEntity.ok(message);
    }






}
