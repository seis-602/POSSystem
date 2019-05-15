package com.seis602.possystem.config;

import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.seis602.possystem.model.CashRegister;
import com.seis602.possystem.model.Product;
import com.seis602.possystem.model.User;
import com.seis602.possystem.model.Shift;
import com.seis602.possystem.repository.CashRegisterRepository;
import com.seis602.possystem.repository.ProductRepository;
import com.seis602.possystem.repository.UserRepository;
import com.seis602.possystem.repository.SaleRepository;
import com.seis602.possystem.repository.ShiftRepository;

@Configuration
public class DatabaseConfiguration {
	
	@Bean
	CommandLineRunner commandLineRunner(
				CashRegisterRepository cashRegisterRepository,
				ProductRepository productRepository, 
				SaleRepository saleRepository,
				UserRepository userRepository,
				ShiftRepository shiftRepository
			) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				// database seeder
				User user1 = new User("al_einstein@gmail.com", "relativity", "Albert Einstein", "Albert", "Einstein");
				User user2 = new User("donkey_kong@gmail.com", "donkey", "Donkey Kong", "Donkey", "Kong");

				userRepository.save(user1);
				userRepository.save(user2);
				userRepository.save(new User("thisismyemail@gmail.com", "thisismypassword", "alinaqvi", "Ali", "Naqvi"));
				userRepository.save(new User("testing@gmail.com", "123", "test", "Rhyan", "Foo Kune"));
				
				productRepository.save(new Product("Bread", "Food", 300, "Walmart", 70, 0, 0));
				productRepository.save(new Product("Milk", "Food", 200, "Target", 40, 0, 40));
				productRepository.save(new Product("Cheese", "Food", 400, "Target", 0, 50, 0));
				productRepository.save(new Product("Ham", "Food", 500, "Walmart", 100, 0, 0));
				productRepository.save(new Product("Soap", "Health & Beauty", 600, "Target", 50, 0, 50));
				productRepository.save(new Product("Shampoo", "Health & Beauty", 700, "Target", 110, 0, 100));

				cashRegisterRepository.save(new CashRegister("Cash Register 1", 10000));
				cashRegisterRepository.save(new CashRegister("Cash Register 2", 0));
				cashRegisterRepository.save(new CashRegister("Cash Register 3", 15000));
				
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime1 = format.parse("2019-05-15 8:12:17");
				Date endTime1 = format.parse("2019-05-15 13:23:20");
				Date startTime2 = format.parse("2019-05-15 11:45:56");
				Date endTime2 = format.parse("2019-05-15 15:59:11");
				
				Shift shift1 = new Shift(user1, startTime1, endTime1, 152231);
				Shift shift2 = new Shift(user2, startTime2, endTime2, 95050);
				
				shiftRepository.save(shift1);
				shiftRepository.save(shift2);
				
			}
			
		};
	}

}


