package com.hackathon.bobFriend.users.dto;

import com.hackathon.bobFriend.users.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    //@Pattern(regexp = "")
    private String password;

    public static UserEntity toEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}
