package com.fresco.wingst4restwebapispringbootfp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.wingst4restwebapispringbootfp.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {
}