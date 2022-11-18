package com.practice.springboottdd.service;

import com.practice.springboottdd.domain.dto.SignUpRequest;
import com.practice.springboottdd.domain.dto.SignUpResponse;
import com.practice.springboottdd.domain.entity.User;
import com.practice.springboottdd.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository); // 이전처럼 WebMvcTest 를 붙여서 DI 받는 것이 아니라 수동으로 DI 해준다.
    }

    @Test
    @DisplayName("회원 등록 성공 메시지가 나오는지")
    void signUpSuccessTest() {
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1L, "sjeon0730", "12345678"));

        Mockito.when(userRepository.findByUsername("sjeon0730"))
                .thenReturn(Optional.empty());

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username("sjeon0730")
                .password("12345678")
                .build();

        SignUpResponse signUpResponse = userService.signUpUser(signUpRequest);
        assertEquals("가입이 완료되었습니다.", signUpResponse.getMessage());
    }

    @Test
    @DisplayName("회원 등록 실패 메시지가 나오는지")
    void signUpFailedTest() {
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1L, "sjeon0730", "12345678"));

        Mockito.when(userRepository.findByUsername("sjeon0730"))
                .thenReturn(Optional.of(new User(1L, "sjeon0730", "12345678")));

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username("sjeon0730")
                .password("12345678")
                .build();

        SignUpResponse signUpResponse = userService.signUpUser(signUpRequest);
        assertEquals("이미 존재하는 아이디입니다.", signUpResponse.getMessage());
    }

}