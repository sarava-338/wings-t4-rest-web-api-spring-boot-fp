package com.fresco.wingst4restwebapispringbootfp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.wingst4restwebapispringbootfp.models.Cart;


public interface CartRepo extends JpaRepository<Cart, Integer> {
}