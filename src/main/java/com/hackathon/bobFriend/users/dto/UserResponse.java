package com.hackathon.bobFriend.users.dto;

import com.hackathon.bobFriend.users.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long userId;

    private String name;

    private String email;

    private String password;

    private Boolean isActive;

    private LocalDateTime createdAt;

    // private List<ReviewResponse> reviewList() {}

    public static UserResponse toDto(UserEntity userEntity) {
        return UserResponse.builder()
                .userId(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .isActive(userEntity.getIsActive())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }
}
