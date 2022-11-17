package com.practice.springboottdd.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponse {

    private Long id;
    private String username;
    private String password;
    private String message;

    public UserResponse() {
    }

    public UserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserResponse(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
