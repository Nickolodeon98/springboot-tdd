package com.practice.springboottdd.domain.entity;

import com.practice.springboottdd.domain.dto.SignUpResponse;
import com.practice.springboottdd.domain.dto.UserResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name="user")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String username;
    String password;

    public static UserResponse of(User user) {
        if (user == null) return new UserResponse(null, null, null);
        return new UserResponse(user.getId(), user.getUsername());
    }

    public static SignUpResponse ofSignUpResponse(User user) {

        return SignUpResponse.builder()
                .username(user.getUsername())
                .message("가입이 완료되었습니다.")
                .build();
    }
}
