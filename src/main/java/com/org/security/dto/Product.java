package com.org.security.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



public class Product {
	
private int id;
	
	
	private String productName;
	
	
//	private ProductCategory productCategory;
	
	private int categoryId;
	
	
	
	
	
	
	
//	public ProductCategory getProductCategory() {
//		return productCategory;
//	}
//
//	public void setProductCategory(ProductCategory productCategory) {
//		this.productCategory = productCategory;
//	}


	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	private double price;
	
	private String description;
	private String imageName;
	
	
	

	
	
	

	

	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
	private String quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	

	

	

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", categoryId=" + categoryId + ", price=" + price
				+ ", description=" + description + ", imageName=" + imageName + ", quantity=" + quantity + "]";
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String productName, int categoryId, double price, String description, String imageName,
			String quantity) {
		super();
		this.id = id;
		this.productName = productName;
		this.categoryId = categoryId;
		this.price = price;
		this.description = description;
		this.imageName = imageName;
		this.quantity = quantity;
	}

	

	

	

	

}
