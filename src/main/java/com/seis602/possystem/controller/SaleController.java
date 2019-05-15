package com.seis602.possystem.controller;

import java.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	private ArrayList<String> shoppingCart;
	private int saleID = 1;
	//Product.java needs to have variables converted to double.
	
	@RequestMapping("/sales")
	public List<Sale>getAllSales() {
		return saleService.getAllSales();
	}
	
	/*@GetMapping("/allSales") 
	public String index(Model model){
		List<Sale> sales = saleService.getAllSales();
		String jsonSales = new Gson().toJson(sales);
		model.addAttribute("allSales", jsonSales);
		return "sales/index";
	}*/
	
	@RequestMapping(method=RequestMethod.POST, value="/sales")
	public void addSale(@RequestBody Sale sale) {
		saleService.addSale(sale);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cash-registers/{cashRegisterId}/sales")
	public void postSale(@PathVariable Integer cashRegisterId, @RequestBody Map<String, Object> payload) throws Exception {
		
		this.shoppingCart = new ArrayList<String>();
		Date saleDate = new Date();
		ArrayList<Object> items = (ArrayList) payload.get("cart_items");
		items.forEach((Object item) -> getProducts(item));
		
		Double saleTotal = new Double(payload.get("amount_due").toString());
		updateBalanceInCashRegister(cashRegisterId, saleTotal);
		sale.save(new Sale(saleID, shoppingCart, saleTotal, 1, saleDate, cashRegisterId));
		saleID++;
	}
	
	public void getProducts(Object item) {
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> mapItem = oMapper.convertValue(item, Map.class);
		Integer productID = (int) mapItem.get("product_id");
		Object quantity = mapItem.get("quantity");
		Product p = products.getProduct(productID);
		this.shoppingCart.add(toString(p, quantity));
		updateInventory(productID, transformQuantity(quantity));
		mapItem.clear();
	}
	
	public void updateInventory(Integer productID, Double quantity) {
		Product p = products.getProduct(productID);
		int remaining = p.getRemaining();
		remaining -= quantity;
		if(remaining == p.getRequestedAmount()) {
			reorderProducts(p);
		} else { 
			p.setRemaining(remaining);
		}
	}
	
	public void updateBalanceInCashRegister(Integer cashRegisterID, Double saleTotal) {
		CashRegister cR = cashRegister.getCashRegisterByID(cashRegisterID);
		int balance = cR.getBalance();
		balance += saleTotal;
		cR.setBalance(balance);
	}
	
	public void reorderProducts(Product p) {
		//Not sure how we want to handle this,
		//Right now I'm just re-stocking back to the requested/threshold amount
		p.setRemaining(p.getRequestedAmount());
	}
	
	public double transformQuantity(Object q) {
		Double quantity = (Double.parseDouble(q.toString())/100);
		return quantity;
	}
	
	public String toString(Product p, Object quantity) {
		return "Product: " + p.getName() + ", Price: " + p.getPrice() + ", Quantity: " + quantity.toString();
	}
}
