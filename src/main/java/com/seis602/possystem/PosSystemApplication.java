package com.seis602.possystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PosSystemApplication {
	
	public static void main(String[] args) {
		
	    SpringApplication.run(PosSystemApplication.class, args);
	    
	}

}
