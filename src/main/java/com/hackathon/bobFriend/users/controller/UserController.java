package com.hackathon.bobFriend.users.controller;

import com.hackathon.bobFriend.users.dto.LoginRequest;
import com.hackathon.bobFriend.users.dto.UserRequest;
import com.hackathon.bobFriend.users.dto.UserResponse;
import com.hackathon.bobFriend.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping("/auth")
    public ResponseEntity<Map<String,String>> login(@RequestParam String email, @RequestParam String password) {
  
        LoginRequest loginRequest = new LoginRequest(email, password);
        String token = "";
        Map<String, Object> map = new HashMap<>();
        try {
            token = userService.login(loginRequest);
            System.out.println("token generated : " + token);

            map.put("token", token);
            map.put("user", userService.getUserByEmail(email));
        }
        catch(Exception e){
            System.out.println("Login failed");
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
        return ResponseEntity.ok().body(map);
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
