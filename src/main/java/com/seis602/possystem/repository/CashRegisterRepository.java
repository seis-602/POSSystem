package com.seis602.possystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seis602.possystem.model.CashRegister;

public interface CashRegisterRepository extends JpaRepository<CashRegister, Integer>{

}