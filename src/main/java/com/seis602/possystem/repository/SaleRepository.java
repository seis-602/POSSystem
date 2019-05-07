package com.seis602.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seis602.possystem.model.Sale;
import com.seis602.possystem.service.SaleService;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
