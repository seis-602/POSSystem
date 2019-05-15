package com.seis602.possystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis602.possystem.model.Sale;
import com.seis602.possystem.model.User;
import com.seis602.possystem.repository.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	public List<Sale> getAllSales() {
		List<Sale> sales = new ArrayList<>();
		saleRepository.findAll().forEach(sales::add);
		return sales;
	}
	
	public List<Sale> getSalesByUser(User user) {
		List<Sale> sales = new ArrayList<>();
		saleRepository.findAllByUser(user).forEach(sales::add);
		return sales;
	}
	
	public Sale getSale(Integer id) {
		return saleRepository.findById(id).orElse(null);
	}
	
	public Sale saveSale(Sale sale) {
		return saleRepository.save(sale);
	}
}
