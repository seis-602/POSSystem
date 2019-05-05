package com.seis602.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seis602.possystem.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
