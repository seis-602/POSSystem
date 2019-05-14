package com.seis602.possystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seis602.possystem.model.Shift;
import com.seis602.possystem.model.User;

public interface ShiftRepository extends JpaRepository<Shift, Integer>{

	Optional<Shift> findByUserAndEndTimeIsNull(User user);
	
}
