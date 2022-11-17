package com.practice.springboottdd.controller;

import com.practice.springboottdd.domain.dto.UserResponse;
import com.practice.springboottdd.domain.entity.User;
import com.practice.springboottdd.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .username("sjeon0730")
                .build(); // 패스워드는 제외하고 리턴
        
        given(userService.getSingleUser(1L)).willReturn(userResponse);


        String url = "/api/v1/users";
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").exists())
                .andDo(print());

        verify(userService).getSingleUser(1L);
    }
}