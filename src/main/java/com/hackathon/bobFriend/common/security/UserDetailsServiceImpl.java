package com.hackathon.bobFriend.common.security;

import com.hackathon.bobFriend.users.entity.UserEntity;
import com.hackathon.bobFriend.users.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsImpl loadUserById(Long id) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("UserEntity Not Found"));

        return new UserDetailsImpl(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Not found " + email));

        return new UserDetailsImpl(user);
    }
}