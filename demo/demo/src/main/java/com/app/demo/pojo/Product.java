package com.app.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 20, nullable = false)
	private String productName;
	
	@Column(name = "cost", nullable = false)
	private float cost;
	
	@Column(name = "color", nullable = false)
	private String color;
	
	@Column(name = "storage", nullable = false)
	private int storage;
	
	@Column(name = "ram", nullable = false)
	private int ram;
	
	@Column(name = "operator", nullable = false)
	private String operator;
	
	@Column(name = "rating", nullable = false)
	private int rating;

	public Product() 
	{
		System.out.println("Product pojo");
	}

	public Product(int id, String productName, float cost, String color, int storage, int ram, String operator, int rating) 
	{
		super();
		this.id = id;
		this.productName = productName;
		this.cost = cost;
		this.color = color;
		this.storage = storage;
		this.ram = ram;
		this.operator = operator;
		this.rating = rating;
	}

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

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", cost=" + cost + ", color=" + color
				+ ", storage=" + storage + ", ram=" + ram + ", operator=" + operator + ", rating=" + rating + "]";
	}
	
}
