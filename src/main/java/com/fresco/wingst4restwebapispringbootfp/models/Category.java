package com.fresco.wingst4restwebapispringbootfp.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@JsonIgnore
	private Integer categoryId;
	@Column(unique = true)
	private String categoryName;

	public Category() {
		super();
	}

	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}

}