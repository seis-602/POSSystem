package com.seis602.possystem.controller;

import java.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.model.Product;
import com.seis602.possystem.model.Sale;
import com.seis602.possystem.model.CashRegister;
import com.seis602.possystem.service.SaleService;
import com.seis602.possystem.service.ProductService;
import com.seis602.possystem.service.CashRegisterService;
import com.seis602.possystem.repository.SaleRepository;

@RestController
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	@Autowired
	private SaleRepository sale;
	@Autowired
	private ProductService products;
	@Autowired
	private CashRegisterService cashRegister;
	
	private Map<Integer, Product> shoppingCart;
	private int key = 1;
	private int saleID = 1;
	//Product.java needs to have variables converted to double.
	
	@RequestMapping("/sales")
	public List<Sale>getAllSales() {
		return saleService.getAllSales();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/sales")
	public void addSale(@RequestBody Sale sale) {
		saleService.addSale(sale);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cash-registers/{cashRegisterId}/sales")
	public void postSale(@PathVariable Integer cashRegisterId, @RequestBody Map<String, Object> payload) throws Exception {
		
		this.shoppingCart = new HashMap<Integer, Product>();
		Date saleDate = new Date();
		ArrayList<Object> items = (ArrayList) payload.get("cart_items");
		items.forEach((Object item) -> getProducts(item));
		Double saleTotal = new Double(payload.get("amount_due").toString());
		updateBalanceInCashRegister(cashRegisterId, 3);
		sale.save(new Sale(saleID, shoppingCart, saleTotal, 1, saleDate, cashRegisterId));
		saleID++;
	}
	
	public void getProducts(Object item) {
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> mapItem = oMapper.convertValue(item, Map.class);
		Integer productID = (int) mapItem.get("product_id");
		Integer quantity = (int) mapItem.get("quantity");
		Product p = products.getProduct(productID);
		this.shoppingCart.put(key, p);
		updateInventory(productID, quantity);
		mapItem.clear();
		key++;
	}
	
	public void updateInventory(Integer productID, Integer quantity) {
		Product p = products.getProduct(productID);
		int remaining = p.getRemaining();
		remaining -= quantity;
		if(remaining == p.getRequestedAmount()) {
			reorderProducts(p);
		} else { 
			p.setRemaining(remaining);
		}
	}
	
	public void updateBalanceInCashRegister(Integer cashRegisterID, Integer saleTotal) {
		CashRegister cR = cashRegister.getCashRegisterByID(cashRegisterID);
		int balance = cR.getBalance();
		balance += saleTotal;
		cR.setBalance(balance);
	}
	
	public void reorderProducts(Product p) {
		//Not sure how we want to handle this,
		//right now I'm just re-stocking back to the requested amount
		p.setRemaining(p.getRequestedAmount());
	}
}
