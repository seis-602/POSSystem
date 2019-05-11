package com.seis602.possystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
	
	@GetMapping("/products/create")
	public String create(){
		return "products/create";
	}

}
