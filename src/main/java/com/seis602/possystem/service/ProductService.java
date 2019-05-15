package com.seis602.possystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seis602.possystem.model.Product;
import com.seis602.possystem.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	
	public Product getProduct(int id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void deleteProduct(int id) {
		Product product = this.getProduct(id);
		productRepository.delete(product);
    }
	
	public Product setRequestedInventory(Product product, int amount) {
		product.setRequestedAmount(product.getRequestedAmount() + amount);
		return productRepository.save(product);
	}
	
	public Product setReceivedInventory(Product product, int amountReceived) {
		
		int requestedAmount = product.getRequestedAmount() - amountReceived;
		int inventoryAmount = product.getRemaining() + amountReceived;
		
		product.setRequestedAmount(requestedAmount);
		product.setRemaining(inventoryAmount);
		
		return productRepository.save(product);
	}
	
}
