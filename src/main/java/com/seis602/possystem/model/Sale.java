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
	//Item objects
	@ElementCollection
	@CollectionTable(name="PRODUCTS")
	@Column(name = "Product_List")
	private Map<Integer, Product> productList;
	//Amount
	@Column(name = "sale_total")
	private Double saleTotal;
	@Column(name = "userID")
	private Integer userID;
	@Column(name = "dateOfSale")
	private Date dateAndTime;
	@Column(name = "registerID")
	private Integer registerID;
	
	public Sale() {
		
	}

	public Sale(int id, Map<Integer, Product> productList, Double saleTotal, Integer userID, Date dateAndTime, Integer registerID) {
		super();
		this.id = id;
		this.productList = productList;
		this.saleTotal = saleTotal;
		this.dateAndTime = dateAndTime;
		this.userID = userID;
		this.registerID = registerID;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public Object getSaleTotal() {
		return saleTotal;
	}
	
	public Map<Integer, Product> getProductList() {
		return this.productList;
	}
	
	public Integer getUserID() {
		return this.userID;
	}
	
	public Date getDateAndTime() {
		return this.dateAndTime;
	}
	
	public Integer getRegisterID() {
		return this.registerID;
	}
}
