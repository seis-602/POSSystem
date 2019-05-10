package com.seis602.possystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.model.Product;
import com.seis602.possystem.service.ProductService;

@RestController
public class ApiProductController {

	@Autowired
	private ProductService productService;	
	
	@RequestMapping(method=RequestMethod.POST, value="/products")
	public Product store(@RequestBody Product product) {
		Product savedProduct = productService.saveProduct(product);
		return savedProduct;
		
	}
	
	@DeleteMapping("/products/{id}")
    public ResponseEntity destroy(@PathVariable int id) {
        
		if (productService.getProduct(id) == null) {
            ResponseEntity.badRequest().build();
        }

        productService.deleteProduct(id);

        return ResponseEntity.ok().build();
    }
}
