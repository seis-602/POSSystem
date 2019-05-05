package com.seis602.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.model.Product;
import com.seis602.possystem.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/guest/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

}
