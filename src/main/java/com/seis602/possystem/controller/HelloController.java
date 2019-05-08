package com.seis602.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.model.CashRegister;
import com.seis602.possystem.service.CashRegisterService;

//@RequestMapping("/rest/hello")
@RestController
public class HelloController {

	@GetMapping("/guest/hello")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/secured")
	public String securedHello() {
		return "Secured Hello";
	}
	
	@Autowired
	private CashRegisterService cashRegisterService;
	
	@RequestMapping("/guest/cash-registers")
	public List<CashRegister> getAllCashRegisters() {
		return cashRegisterService.getAllCashRegisters();
	}

}
