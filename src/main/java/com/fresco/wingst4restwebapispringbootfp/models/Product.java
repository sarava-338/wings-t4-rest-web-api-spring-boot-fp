package com.fresco.wingst4restwebapispringbootfp.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer productId;
	private String productName;
	private Double price;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "seller_id", referencedColumnName = "userId", updatable = false)
	@JsonIgnore
	private User seller;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id", referencedColumnName = "categoryId")
	private Category category;

	public Product() {
		super();
	}

	public Product(Integer productId, String productName, Double price, User seller, Category category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.seller = seller;
		this.category = category;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [productId=" + 0 + ", productName=" + productName + ", price=" + price + ", seller="
				+ seller.getUserId() + ", category=" + category + "]";
	}

}