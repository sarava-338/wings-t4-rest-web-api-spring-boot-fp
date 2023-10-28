package com.fresco.wingst4restwebapispringbootfp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.wingst4restwebapispringbootfp.models.CartProduct;


public interface CartProductRepo extends JpaRepository<CartProduct, Integer> {
}