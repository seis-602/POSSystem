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
				userRepository.save(new User(1, "testing@gmail.com", "123", "test", "Rhyan", "Foo Kune"));
				
				productRepository.save(new Product(1, "Bread", "Food", 300, 7));
				productRepository.save(new Product(2, "Milk", "Food", 200, 4));
				productRepository.save(new Product(3, "Cheese", "Food", 400, 0));
				productRepository.save(new Product(4, "Ham", "Food", 500, 20));
				productRepository.save(new Product(5, "Soap", "Health & Beauty", 600, 5));
				productRepository.save(new Product(6, "Shampoo", "Health & Beauty", 700, 10));
				
				cashRegisterRepository.save(new CashRegister(1, "Cash Register 1"));
				cashRegisterRepository.save(new CashRegister(2, "Cash Register 2"));
				Date d = new Date();
				Map<Integer, Product> shoppingCart;
				//Change to product later
				shoppingCart = new HashMap<Integer, Product>();
				
				Product p = new Product(7, "Test", "Test124", 300, 2);
				Product z = new Product(8, "Test2", "Test3", 400, 3);
				productRepository.save(p);
				productRepository.save(z);
				shoppingCart.put(1, p);
				shoppingCart.put(2, z);
				saleRepository.save(new Sale(2, shoppingCart, 100.00, 1, d, 3));
				//saleRepository.save(new Sale(3, shoppingCart, 115.00, 1, d));
			}
			
		};
	}

}


