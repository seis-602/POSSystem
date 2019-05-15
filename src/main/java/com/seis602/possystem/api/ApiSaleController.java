package com.seis602.possystem.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seis602.possystem.model.CashRegister;
import com.seis602.possystem.model.CustomUserDetails;
import com.seis602.possystem.model.Product;
import com.seis602.possystem.model.Sale;
import com.seis602.possystem.model.Shift;
import com.seis602.possystem.model.User;
import com.seis602.possystem.service.CashRegisterService;
import com.seis602.possystem.service.ProductService;
import com.seis602.possystem.service.SaleService;
import com.seis602.possystem.service.ShiftService;
import com.seis602.possystem.service.UserService;

@RestController
public class ApiSaleController {
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private CashRegisterService cashRegisterService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/api/sales")
	public List<Sale>getAllSales() {
		return saleService.getAllSales();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cash-registers/{cashRegisterId}/sales")
	public int postSale(@PathVariable Integer cashRegisterId, @RequestBody Map<String, Object> payload) throws Exception {
		
		Date saleDate = new Date();
		Integer saleTotal = new Integer(payload.get("amount_due").toString());
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int userId = ((CustomUserDetails)principal).getId();
		User user = userService.getUser(userId);
		
		ArrayList<Object> items = (ArrayList) payload.get("cart_items");
		List<Product> shoppingCart = this.recordSale(items);
		
		updateBalanceInCashRegister(cashRegisterId, saleTotal);
		updateShiftDrawerAmount(user, saleTotal);
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String jsonProducts = gson.toJson(shoppingCart);
		
		CashRegister cashRegister = cashRegisterService.getCashRegisterByID(cashRegisterId);
		Sale sale = new Sale(user, cashRegister, jsonProducts, saleTotal, saleDate);
		saleService.saveSale(sale);
		
		return sale.getId();
	}
	
	
	private List<Product> recordSale(ArrayList<Object> items) {
		
		List<Product> shoppingCart = new ArrayList<Product>();
		
		for (Object item: items) {
			
			ObjectMapper oMapper = new ObjectMapper();
			Map<String, Object> mapItem = oMapper.convertValue(item, Map.class);
			
			Integer productId = (int) mapItem.get("product_id");
			Integer quantity = (int) mapItem.get("quantity");
			
			Product product = productService.getProduct(productId);
			
			for (int i = 0; i < quantity; i++) {
				shoppingCart.add(product);
			}
			
			this.updateInventory(product, quantity);
		}
		
		return shoppingCart;
	}
	
	private void updateInventory(Product product, Integer quantity) {
		Integer left = product.getRemaining() - quantity;
		product.setRemaining(left);
		
		// if inventory left is less than threshold and no item has been requested yet, reorder 50 new items
		if (left < product.getThreshold() && product.getRequestedAmount() == 0) {
			product.setRequestedAmount(50);
		}
		
		productService.saveProduct(product);
	}
	
	public void updateBalanceInCashRegister(Integer cashRegisterId, Integer saleTotal) {
		CashRegister cashRegister = cashRegisterService.getCashRegisterByID(cashRegisterId);
		cashRegister.setBalance(cashRegister.getBalance() + saleTotal);
	}
	
	public void updateShiftDrawerAmount(User user, Integer saleTotal) {
		Shift shift = shiftService.getActiveShiftByUser(user);
		shift.setDrawerAmount(shift.getDrawerAmount() + saleTotal);
		shiftService.saveShift(shift);
	}
}
