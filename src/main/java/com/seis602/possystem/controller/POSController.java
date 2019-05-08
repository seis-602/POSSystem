package com.seis602.possystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.seis602.possystem.model.CashRegister;
import com.seis602.possystem.model.Product;
import com.seis602.possystem.service.CashRegisterService;
import com.seis602.possystem.service.ProductService;

@Controller
public class POSController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CashRegisterService cashRegisterService;
	
	@RequestMapping(value="/cash-registers/{cashRegisterId}/point-of-sale", method=RequestMethod.GET)
	 public String findOwner(@PathVariable Integer cashRegisterId, Model model) {
		List<Product> products = productService.getAllProducts();
		
		Optional<CashRegister> cashRegister = cashRegisterService.getCashRegister(cashRegisterId);
		// handle exception - if cashRegister is null
		
		String jsonProducts = new Gson().toJson(products);
		String jsonCashRegister = new Gson().toJson(cashRegister);
		
		model.addAttribute("products", jsonProducts);
		model.addAttribute("cashRegister", jsonCashRegister);
		
		return "pos";
	 }
	
}
