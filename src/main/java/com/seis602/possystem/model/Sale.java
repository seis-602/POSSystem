package com.seis602.possystem.model;

import java.lang.reflect.Type;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ElementCollection;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Entity
@Table(name = "sales")
public class Sale {
	
	private static int idCount = 1;
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(columnDefinition="clob")
	@Lob
	private String products;
	
	@Column(name = "sale_total")
	private Integer saleTotal;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	@Column(name = "date_of_sale")
	private Date dateOfSale;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cash_register_id", referencedColumnName = "id")
	private CashRegister cashRegister;
	
	public Sale() {
		
	}

	public Sale(User user, CashRegister cashRegister, String products, Integer saleTotal, Date dateOfSale) {
		super();
		this.id = Sale.idCount++;
		this.user = user;
		this.cashRegister = cashRegister;
		this.products = products;
		this.saleTotal = saleTotal;
		this.dateOfSale = dateOfSale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}
	
	public Collection<Product> getProductList() {
		Type collectionType = new TypeToken<Collection<Product>>(){}.getType();
		Collection<Product> productList = new Gson().fromJson(products, collectionType);
		return productList;
	}

	public Integer getSaleTotal() {
		return saleTotal;
	}

	public void setSaleTotal(Integer saleTotal) {
		this.saleTotal = saleTotal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(Date dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

	public CashRegister getCashRegister() {
		return cashRegister;
	}

	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}
}
