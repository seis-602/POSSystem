package com.seis602.possystem.config;

import java.util.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import com.seis602.possystem.model.CashRegister;
import com.seis602.possystem.model.Product;
import com.seis602.possystem.model.User;
import com.seis602.possystem.model.Sale;
import com.seis602.possystem.repository.CashRegisterRepository;
import com.seis602.possystem.repository.ProductRepository;
import com.seis602.possystem.repository.UserRepository;
import com.seis602.possystem.repository.SaleRepository;

@Configuration
public class DatabaseConfiguration {
	
	@Bean
	CommandLineRunner commandLineRunner(
				CashRegisterRepository cashRegisterRepository,
				ProductRepository productRepository, 
				SaleRepository saleRepository,
				UserRepository userRepository
			) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				// database seeder
				userRepository.save(new User("testing@gmail.com", "123", "test", "Rhyan", "Foo Kune"));
				
				productRepository.save(new Product("Bread", "Food", 300, "Walmart", 7, 0));
				productRepository.save(new Product("Milk", "Food", 200, "Target", 4, 0));
				productRepository.save(new Product("Cheese", "Food", 400, "Target", 0, 50));
				productRepository.save(new Product("Ham", "Food", 500, "Walmart", 20, 0));
				productRepository.save(new Product("Soap", "Health & Beauty", 600, "Target", 5, 0));
				productRepository.save(new Product("Shampoo", "Health & Beauty", 700, "Target", 10, 0));
				
				cashRegisterRepository.save(new CashRegister(1, "Cash Register 1", 10000));
				cashRegisterRepository.save(new CashRegister(2, "Cash Register 2", 0));
				cashRegisterRepository.save(new CashRegister(3, "Cash Register 3", 15000));
				
				Map<Integer, String> productList;
				//Change to product later
				productList = new HashMap<Integer, String>();
				productList.put(1, "Test");
				saleRepository.save(new Sale(1, 12, productList, 100.00));
			}
			
		};
	}

}


