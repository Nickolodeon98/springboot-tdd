package com.practice.springboottdd.domain.entity;

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
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
