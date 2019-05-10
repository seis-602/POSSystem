package com.seis602.possystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private int price;
    @Column(name = "remaining")
    private int remaining;
    @Column(name = "requested_amount")
    private int requestedAmount;

	public Product() {
		
    }

	public Product(int id, String name, String category, int price, int remaining, int requestedAmount) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.remaining = remaining;
		this.requestedAmount = requestedAmount;
	}
	
	public int getId() {
		return id;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

	public int getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(int requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
    
}
