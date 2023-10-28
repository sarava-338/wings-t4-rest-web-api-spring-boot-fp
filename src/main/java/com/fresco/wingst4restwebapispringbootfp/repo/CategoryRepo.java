package com.fresco.wingst4restwebapispringbootfp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.wingst4restwebapispringbootfp.models.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer> {
}