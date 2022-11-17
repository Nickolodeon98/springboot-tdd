package com.practice.springboottdd.domain.dto;

import com.practice.springboottdd.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
public class SignUpRequest {
    String username;
    String password;

    public User toEntity() {
        return User.builder() // id 는 저장할 때 자동 생성 된다
                .username(this.username)
                .password(this.password)
                .build();
    }
}
