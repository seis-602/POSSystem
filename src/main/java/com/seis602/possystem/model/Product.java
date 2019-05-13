package com.seis602.possystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	private static int idCount = 1;

	@Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private int price;
    @Column(name = "supplier")
    private String supplier;
    @Column(name = "remaining")
    private int remaining;
    @Column(name = "requested_amount")
    private int requestedAmount;

	public Product() {
		
    }

	public Product(String name, String category, int price, String supplier, int remaining, int requestedAmount) {
		super();
		this.id = this.idCount++;
		this.name = name;
		this.category = category;
		this.price = price;
		this.supplier = supplier;
		this.remaining = remaining;
		this.requestedAmount = requestedAmount;
	}
	
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
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
