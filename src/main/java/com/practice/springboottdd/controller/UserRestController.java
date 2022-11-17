package com.practice.springboottdd.controller;

import com.practice.springboottdd.domain.dto.UserResponse;
import com.practice.springboottdd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> selectSingleRecord(@PathVariable Long id) {
        UserResponse userResponse = userService.getSingleUser(id);
        return ResponseEntity.ok().body(userResponse);
    }

}
