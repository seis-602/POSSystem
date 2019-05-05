package com.seis602.possystem.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.seis602.possystem.model.User;
import com.seis602.possystem.repository.UserRepository;

@Configuration
public class DatabaseConfiguration {
	
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository ) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				userRepository.save(new User(1, "testing@gmail.com", "123", "test", "Rhyan", "Foo Kune"));
				
			}
			
		};
	}

}


