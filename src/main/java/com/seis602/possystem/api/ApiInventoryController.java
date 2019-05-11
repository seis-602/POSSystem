package com.seis602.possystem.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.seis602.possystem.model.Product;
import com.seis602.possystem.service.ProductService;

@RestController
public class ApiInventoryController {
	
	@Autowired
	private ProductService productService;	

	@RequestMapping(method=RequestMethod.POST, value="/inventories/{productId}/request")
	public ResponseEntity requestInventory(@PathVariable Integer productId, @RequestBody Map<String, Object> payload) {

		Product product = productService.getProduct(productId);
		
		if (product == null) {
            ResponseEntity.badRequest().build();
        }
		
		int amount = product.getRequestedAmount() + (int)payload.get("request_amount");
		product.setRequestedAmount(amount);
		
		productService.saveProduct(product);
		
		return ResponseEntity.ok().build();
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/inventories/{productId}/received")
	public ResponseEntity receivedInventory(@PathVariable Integer productId, @RequestBody Map<String, Object> payload) {
		
		Product product = productService.getProduct(productId);

		if (product == null) {
            ResponseEntity.badRequest().build();
        }
		
		int requestedAmount = product.getRequestedAmount() - (int)payload.get("received_amount");
		int inventoryAmount = product.getRemaining() + (int)payload.get("received_amount");
		
		product.setRequestedAmount(requestedAmount);
		product.setRemaining(inventoryAmount);
		
		productService.saveProduct(product);
		
		return ResponseEntity.ok().build();
	}
}
