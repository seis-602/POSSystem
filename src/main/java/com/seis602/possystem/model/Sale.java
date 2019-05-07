package com.seis602.possystem.model;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Table(name = "sales")
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	//Sale number
	@Column(name = "sale_number")
	private Integer saleNumber;
	//Item objects
	@ElementCollection
	@CollectionTable(name="PRODUCTS")
	@MapKeyJoinColumn(name = "Sale_ID")
	@Column(name = "Product_List")
	private Map<Integer, String> productList;
	//Amount
	@Column(name = "sale_amount")
	private Double finalAmount;
	
	public Sale() {
		
	}

	public Sale(int id, int saleNumber, Map productList, Double finalAmount) {
		super();
		this.id = id;
		this.saleNumber = saleNumber;
		this.productList = productList;
		this.finalAmount = finalAmount;
		
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getSaleNumber() {
		return saleNumber;
	}
	
	public double getFinalAmount() {
		return finalAmount;
	}
	
	//public Product getProducts() {
		//return productList;
	//}
	//Implement
	
	
}
