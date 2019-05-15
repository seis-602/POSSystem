package com.seis602.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.Gson;
import com.seis602.possystem.model.Product;
import com.seis602.possystem.model.Sale;
import com.seis602.possystem.service.SaleService;

@Controller
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@GetMapping("/sales")
	public String index(Model model){
		
		List<Sale> sales = saleService.getAllSales();
		String jsonSales = new Gson().toJson(sales);
		model.addAttribute("sales", jsonSales);
		
		return "sales/index";
	}
	
	@GetMapping("/sales/{saleId}")
	public String show(@PathVariable Integer saleId, Model model){
		
		Sale sale = saleService.getSale(saleId);
		
		String jsonSale = new Gson().toJson(sale);
		model.addAttribute("sale", jsonSale);
		
		return "sales/show";
	}
	
}
