package com.practice.springboottdd.service;

import com.practice.springboottdd.domain.dto.SignUpRequest;
import com.practice.springboottdd.domain.dto.SignUpResponse;
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

    public SignUpResponse signUpUser(SignUpRequest signUpRequest) {
        Optional<User> userToSignUp = userRepository.findByUsername(signUpRequest.getUsername());
//        User savedUser = userToSignUp.orElse(userRepository.save(signUpRequest.toEntity())); // 비어있으면 save 후 반환되는 값을 저장한다.
        if (userToSignUp.isPresent())
            return new SignUpResponse(signUpRequest.getUsername(), "이미 존재하는 아이디입니다.");

        User savedUser = userRepository.save(signUpRequest.toEntity());
        return User.ofSignUpResponse(savedUser);
    }
}
