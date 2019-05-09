package com.seis602.possystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.seis602.possystem.model.CashRegister;
import com.seis602.possystem.service.CashRegisterService;

@Controller	
public class CashRegisterController {
	
	@Autowired
	private CashRegisterService cashRegisterService;
	
	@GetMapping("/cash-registers")
	public String cashRegisters(Model model){
        List<CashRegister> cashRegisters = cashRegisterService.getAllCashRegisters();
        String jsonCashRegisters = new Gson().toJson(cashRegisters);
        model.addAttribute("cashRegisters", jsonCashRegisters);
        return "cash-registers/index";
    }
}
