package com.seis602.possystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seis602.possystem.model.Sale;
import com.seis602.possystem.model.Shift;
import com.seis602.possystem.model.User;
import com.seis602.possystem.service.SaleService;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
	
	List<Sale> findAllByUser(User user);
	
}
