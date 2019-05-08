package com.seis602.possystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis602.possystem.model.CashRegister;
import com.seis602.possystem.repository.CashRegisterRepository;

@Service
public class CashRegisterService {

	@Autowired
	private CashRegisterRepository cashRegisterRepository;

	public List<CashRegister> getAllCashRegisters() {
		List<CashRegister> cashRegisters = new ArrayList<>();
		cashRegisterRepository.findAll().forEach(cashRegisters::add);
		return cashRegisters;
	}
	
	public Optional<CashRegister> getCashRegister(Integer id) {
		return cashRegisterRepository.findById(id);
	}
	
}
