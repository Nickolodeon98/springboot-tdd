package com.practice.springboottdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.springboottdd.domain.dto.SignUpRequest;
import com.practice.springboottdd.domain.dto.SignUpResponse;
import com.practice.springboottdd.domain.dto.UserResponse;
import com.practice.springboottdd.domain.entity.User;
import com.practice.springboottdd.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @DisplayName("id 와 username 값을 json 형태로 응답하는지")
    @Test
    void getJsonData() throws Exception {
        UserResponse userResponse = new UserResponse(1L, "sjeon0730"); // 패스워드는 제외하고 리턴
        
        given(userService.getSingleUser(1L)).willReturn(userResponse);

        Long id = 1L;

        String url = String.format("/api/v1/users/%d", id);

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").exists())
                .andDo(print());

        verify(userService).getSingleUser(1L);
    }

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("회원가입 정보를 저장하고 username, message 데이터를 Json 형태로 받는지")
    @Test
    void addAndGetSignUpMessageJson() throws Exception {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username("sjeon0730")
                .password("12345678")
                .build();

        SignUpResponse signUpResponse = SignUpResponse.builder()
                .username("sjeon0730")
                .message("가입이 완료되었습니다.")
                .build();
        given(userService.signUpUser(signUpRequest)).willReturn(signUpResponse);

        String url = "/api/v1/users/credentials";
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(signUpRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.message").exists())
                .andDo(print());

        verify(userService).signUpUser(signUpRequest);

        // 컨트롤러의 어느 부분을 테스트하는건지? 서비스를 잘 호출하고 리턴값이 정확한지?
    }

    /* 중복 검사는 Service 의 일이기 때문에 Service 테스트에서 진행한다. */
//    @DisplayName("중복된 username 으로 회원가입 시 정해진 메시지가 나오는지")
//    @Test
//    void checkDuplicates() {
//        SignUpRequest signUpRequest = SignUpRequest.builder()
//                .username()
//                .build();
//        given(userService.signUpUser()).willReturn()
//    }
}