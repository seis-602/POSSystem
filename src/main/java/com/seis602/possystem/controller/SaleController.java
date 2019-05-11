package com.seis602.possystem.controller;

import java.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.model.Product;
import com.seis602.possystem.model.Sale;
import com.seis602.possystem.service.SaleService;
import com.seis602.possystem.repository.ProductRepository;
import com.seis602.possystem.repository.SaleRepository;

@RestController
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	private SaleRepository sale;
	private ProductRepository products;
	private Map shoppingCart = new HashMap<Integer, Product>();
	
	@RequestMapping("/sales")
	public List<Sale>getAllSales() {
		return saleService.getAllSales();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/sales")
	public void addSale(@RequestBody Sale sale) {
		saleService.addSale(sale);
	}
	
	@Bean
	public Integer addDummySales(SaleRepository saleRepository) {
		Date d = new Date();
		saleRepository.save(new Sale(4, null, 150.00, 1, d, 3));	
		return 1;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cash-registers/{cashRegisterId}/sales")
	public Object postSale(@PathVariable Integer cashRegisterId, @RequestBody Map<String, Object> payload) throws Exception {
		Date saleDate = new Date();
		System.out.println(payload.get("cart_items"));
		Object product = payload.get("cart_items");
		//product.forEach((n) -> System.out.println(n));
		
		Double saleTotal = new Double(payload.get("amount_due").toString());
		Integer registerID = (int) payload.get("cash_register_id");
		//Need to generate unique saleID
		int saleID = 1;
		System.out.println("HERE" + saleID + shoppingCart + saleTotal + 1 + " " + saleDate + " "  + registerID + " " + product.getClass());
		//sale.save(new Sale(saleID, shoppingCart, saleTotal, 1, saleDate, registerID));
		return "done";
	}
	
}
