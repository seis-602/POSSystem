package com.seis602.possystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.Gson;
import com.seis602.possystem.model.Product;
import com.seis602.possystem.service.ProductService;

@Controller
public class InventoryController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/inventories")
	public String index(Model model){
		List<Product> products = productService.getAllProducts();
		String jsonProducts = new Gson().toJson(products);
		model.addAttribute("products", jsonProducts);
		return "inventories/index";
	}
	
	@GetMapping("/inventories/{productId}")
	public String edit(@PathVariable Integer productId, Model model){
		
		Optional<Product> product = productService.getProduct(productId);
		// exception handling
		
		String jsonProduct = new Gson().toJson(product);
		model.addAttribute("product", jsonProduct);
		
		return "inventories/edit";
	}
	
}
