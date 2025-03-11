package com.hackathon.bobFriend.users.controller;

import com.hackathon.bobFriend.users.dto.LoginRequest;
import com.hackathon.bobFriend.users.dto.UserRequest;
import com.hackathon.bobFriend.users.dto.UserResponse;
import com.hackathon.bobFriend.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bobfriend")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestParam String email, @RequestParam String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);
        String token = "";
        try {
            token = userService.login(loginRequest);
        }
        catch(Exception e){
            System.out.println("Login failed");
        }
        return ResponseEntity.ok().body(Map.of("token", token));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        return  ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUser() {

        return  ResponseEntity.ok(userService.getAllUser());
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest) {

        return  ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {

        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
