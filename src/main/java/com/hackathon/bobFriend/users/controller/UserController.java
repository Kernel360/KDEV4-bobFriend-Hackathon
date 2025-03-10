package com.hackathon.bobFriend.users.controller;

import com.hackathon.bobFriend.users.dto.UserRequest;
import com.hackathon.bobFriend.users.dto.UserResponse;
import com.hackathon.bobFriend.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUser() {
        return userService.getAllUser();
    }

    @PatchMapping("/users/{id}")
    public UserResponse updateUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(userRequest);
    }

    @DeleteMapping("/users/{id}")
    public UserResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
