package com.seis602.possystem.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.seis602.possystem.model.CashRegister;
import com.seis602.possystem.model.Sale;
import com.seis602.possystem.service.CashRegisterService;
import com.seis602.possystem.service.SaleService;

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
	
	@RequestMapping(method=RequestMethod.POST, value="/guest/post-test")
	public Object test(HttpServletRequest request) {
		return request.getParameter("test");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cash-registers/{cashRegisterId}/sales")
	public Object postSale(@PathVariable Integer cashRegisterId, @RequestBody Map<String, Object> payload) throws Exception {
		
		System.out.println(payload.get("cash_register_id"));
		System.out.println(payload.get("amount_due"));
		System.out.println(payload.get("cart_items"));
		
		return "done";
	}
}
