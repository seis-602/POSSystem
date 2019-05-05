package com.seis602.possystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis602.possystem.model.User;
import com.seis602.possystem.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public Optional<User> getUser(Integer id) {
		return userRepository.findById(id);
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
}
