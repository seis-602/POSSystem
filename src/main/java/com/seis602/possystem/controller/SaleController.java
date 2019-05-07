package com.seis602.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.model.Sale;
import com.seis602.possystem.service.SaleService;

@RestController
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@RequestMapping()
	public List<Sale>getAllSales() {
		return saleService.getAllSales();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/sales")
	public void addSale(@RequestBody Sale sale) {
		saleService.addSale(sale);
	}
}
