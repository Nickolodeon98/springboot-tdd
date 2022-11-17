package com.practice.springboottdd.controller;

import com.practice.springboottdd.domain.dto.SignUpRequest;
import com.practice.springboottdd.domain.dto.SignUpResponse;
import com.practice.springboottdd.domain.dto.UserResponse;
import com.practice.springboottdd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/credentials")
    public ResponseEntity<SignUpResponse> addSingleUser(@RequestBody SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = userService.signUpUser(signUpRequest);
        return ResponseEntity.ok().body(signUpResponse);
    }
}
