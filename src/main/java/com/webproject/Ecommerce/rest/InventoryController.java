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

import com.webproject.Ecommerce.bo.Availibility;
import com.webproject.Ecommerce.bo.Category;
import com.webproject.Ecommerce.bo.InventoryproductDetails;
import com.webproject.Ecommerce.bo.OrderedProductDetails;
import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Inventory;


import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.service.CustomerService;
import com.webproject.Ecommerce.service.InventoryService;

import com.webproject.Ecommerce.service.SellerService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequestMapping("/inventory")
@RestController
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	
	
	
	@GetMapping
	List<InventoryproductDetails> getInventory(@RequestParam(required = false) String productId,
			@RequestParam(required = false) Category Category,
			@RequestParam(required = false) String subCategory,
			@RequestParam(required = false) String brandName,
			@RequestParam(required = false) String productModelName
			) {
		return inventoryService.getFilter(productId, Category, subCategory, brandName, productModelName);
	}
	
	@PutMapping
	void changeProductStatus(@RequestParam String productId,@RequestParam Availibility status) {
		inventoryService.changeAvailabilityStatus(productId, status);
	}
	

	
	
}
