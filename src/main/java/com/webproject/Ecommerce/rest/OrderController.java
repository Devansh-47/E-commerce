package com.webproject.Ecommerce.rest;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.entity.Order;
import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.service.CustomerService;
import com.webproject.Ecommerce.service.InventoryService;
import com.webproject.Ecommerce.service.OrderService;


import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequestMapping("/order")
@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	

	@PostMapping("/cart")
	String addProductsToCart(@RequestParam String productId,
			@RequestParam String sellerId,
			@RequestParam String inventoryId,
			@RequestParam(defaultValue = "1") Integer Qty) {
		return orderService.addproductToCart(productId, sellerId,inventoryId, Qty);
	}
	
	
	@PatchMapping("/remove/product")
	void removeProductsToCart(@RequestParam String productId,@RequestParam String orderId) {
		orderService.removeproductToCart(orderId, productId);
	}
	
	@PostMapping
	String placeOrder(@RequestParam String orderId,
					@RequestBody Order order) {
		return orderService.placeOrder(orderId,order);
	}
	
	
}
