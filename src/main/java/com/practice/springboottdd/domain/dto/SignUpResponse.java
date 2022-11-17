package com.practice.springboottdd.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class SignUpResponse {
    String username;
    String message;
}
