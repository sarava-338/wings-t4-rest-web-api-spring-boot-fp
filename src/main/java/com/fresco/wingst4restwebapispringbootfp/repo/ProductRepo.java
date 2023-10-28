package com.fresco.wingst4restwebapispringbootfp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.wingst4restwebapispringbootfp.models.Product;


public interface ProductRepo extends JpaRepository<Product, Integer> {
}