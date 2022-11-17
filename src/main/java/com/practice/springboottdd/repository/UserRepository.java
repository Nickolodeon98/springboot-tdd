package com.practice.springboottdd.repository;

import com.practice.springboottdd.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
