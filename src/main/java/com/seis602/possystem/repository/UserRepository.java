package com.seis602.possystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seis602.possystem.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
