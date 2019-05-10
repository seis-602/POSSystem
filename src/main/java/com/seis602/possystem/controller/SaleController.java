package com.seis602.possystem.controller;

import java.util.List;
import java.util.*;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.model.Sale;
import com.seis602.possystem.service.SaleService;
import com.seis602.possystem.repository.SaleRepository;

@RestController
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
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
	
	
}
