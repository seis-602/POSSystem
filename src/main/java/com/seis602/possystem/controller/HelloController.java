package com.seis602.possystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/rest/hello")
@RestController
public class HelloController {

	@GetMapping("/guest/hello")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/secured")
	public String securedHello() {
		return "Secured Hello";
	}
}
