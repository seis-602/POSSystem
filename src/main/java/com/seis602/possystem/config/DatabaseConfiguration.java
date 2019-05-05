package com.seis602.possystem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import com.seis602.possystem.model.Product;
import com.seis602.possystem.model.User;
import com.seis602.possystem.repository.ProductRepository;
import com.seis602.possystem.repository.UserRepository;

@Configuration
public class DatabaseConfiguration {
	
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, ProductRepository productRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				// database seeder
				userRepository.save(new User(1, "testing@gmail.com", "123", "test", "Rhyan", "Foo Kune"));
				
				productRepository.save(new Product(1, "Bread", "Food", 300, 7));
				productRepository.save(new Product(2, "Milk", "Food", 200, 4));
				productRepository.save(new Product(3, "Cheese", "Food", 400, 0));
				productRepository.save(new Product(4, "Ham", "Food", 500, 20));
				productRepository.save(new Product(5, "Soap", "Health & Beauty", 600, 5));
				productRepository.save(new Product(6, "Shampoo", "Health & Beauty", 700, 10));
				
			}
			
		};
	}

}


