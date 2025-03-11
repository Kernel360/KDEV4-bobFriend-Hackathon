package com.hackathon.bobFriend.users.service;

import com.hackathon.bobFriend.common.security.JwtProvider;
import com.hackathon.bobFriend.users.dto.LoginRequest;
import com.hackathon.bobFriend.users.dto.UserRequest;
import com.hackathon.bobFriend.users.dto.UserResponse;
import com.hackathon.bobFriend.users.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    // 회원 가입
    public UserResponse createUser(UserRequest userRequest) {

        var entity = UserRequest.toEntity(userRequest);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return UserResponse.toDto(userRepository.save(entity));
    }

    // 로그인
    public String login(LoginRequest loginRequest) {
        var entity = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with email : [%s] not found", loginRequest.getEmail())));
        if(!passwordEncoder.matches(loginRequest.getPassword(), entity.getPassword())) {
            throw new EntityNotFoundException("invalid password");
        }
        return jwtProvider.createToken(loginRequest.getEmail());
    }

    // 마이페이지
    public UserResponse getUserById(Long id) {

        var entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id : [%s] not found", id)));

        return UserResponse.toDto(entity);
    }

    public UserResponse getUserByEmail(String email) {
        var entity = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(String.format("User with email : [%s] not found", email)));

        return UserResponse.toDto(entity);
    }

    // 관리자 - 전체 회원 조회
    public List<UserResponse> getAllUser() {

        var list = userRepository.findAll();

        return list.stream()
                .map(UserResponse::toDto)
                .toList();
    }

    // 마이페이지 - 회원 정보 수정
    public UserResponse updateUser(UserRequest userRequest) {

        var entity = userRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with email : [%s] not found", userRequest.getEmail())));

            entity.setName(userRequest.getName());
            entity.setEmail(userRequest.getEmail());
            entity.setPassword(passwordEncoder.encode(userRequest.getPassword()));

            return UserResponse.toDto(userRepository.save(entity));
    }

    // 마이페이지 - 회원 탈퇴
    public UserResponse deleteUser(Long id) {

        var entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id : [%s] not found", id)));

        entity.setIsActive(false);
        return UserResponse.toDto(userRepository.save(entity));
    }
}
