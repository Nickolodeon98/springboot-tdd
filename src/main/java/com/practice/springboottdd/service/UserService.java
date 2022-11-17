package com.practice.springboottdd.service;

import com.practice.springboottdd.domain.dto.UserResponse;
import com.practice.springboottdd.domain.entity.User;
import com.practice.springboottdd.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getSingleUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return User.of(user.get());
    }
}
