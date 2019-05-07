package com.seis602.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.seis602.possystem.model.Product;
import com.seis602.possystem.service.ProductService;

@Controller
public class POSController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/point-of-sale")
	public String pos(Model model){
        List<Product> products = productService.getAllProducts();
        String jsonProducts = new Gson().toJson(products);
        model.addAttribute("products", jsonProducts);
        return "pos";
    }
	
}
