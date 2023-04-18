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

import com.webproject.Ecommerce.bo.SellerProductDetails;
import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.service.CustomerService;

import com.webproject.Ecommerce.service.SellerService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequestMapping("/seller")
@RestController
public class SellerController {

	
	@Autowired
	SellerService sellerService;

	@GetMapping
	List<Seller> getAllSellers
		   (
			@RequestParam(required = false) String sellerId,
			@RequestParam(required = false) String sellerName,
			@RequestParam(required = false) String sellerEmail,
			@RequestParam(required = false) String sellerMobile) {
		return sellerService.getBy(sellerId, sellerName, sellerEmail, sellerMobile);
	}
	
	
	@GetMapping("/myProducts")
	List<SellerProductDetails> getAllSellerProducts
		   (
			@RequestParam(required = false) String sellerId) {
		return sellerService.getSellerproducts(sellerId);
	}
	
	
	
	
	@PostMapping("/create")
	void createSeller(@Valid@RequestBody Seller seller) {
		sellerService.createSeller(seller);
		log.info("seller Created with id="+seller.getSellerId());
	}
	
	

	@DeleteMapping
	void hardDeleteSeller(@RequestParam String id) {
		sellerService.DeleteSellerById(id);
		log.info("seller deleted with id="+id);
	}
	
	@PutMapping
	void updateSeller(@RequestParam String id,@RequestBody Seller seller) {
		sellerService.upadateSeller(id, seller);
		log.info("Updated rocored having id = "+id+" "+seller.getSellerId());
	}
	
	@PutMapping("/newproducts")
	void addNewProducts(@RequestParam String sellerId,@Valid@RequestBody List<Product> productList) {
		sellerService.addNewproducts(sellerId, productList);
	}
	
	@PutMapping("/existingproducts")
	void addExistingProducts(@RequestParam String sellerId,@RequestBody List<String> productIdsList) {
		sellerService.addExistingproducts(sellerId, productIdsList);
	}
	
	@PatchMapping("/Qty")
	void addProductQty(@RequestParam String sellerId, @RequestParam String productId,@RequestParam int Qty) {
		sellerService.addproductQuantity(sellerId, productId, Qty);
	}
	
	@PatchMapping("Price")
	void updateProductPrice(@RequestParam String sellerId, @RequestParam String productId,@RequestParam Double price) {
		sellerService.updateProductPrice(sellerId, productId, price);
	}
	
	@PatchMapping("/stopSelling")
	void stopSellingProduct(@RequestParam String sellerId,@RequestParam String productId) {
		sellerService.stopSellingProduct(sellerId, productId);
	}
	
	
	@PostMapping("/updateProduct")
	String updateProductDetails(@RequestParam String sellerId,@RequestParam String productId,@RequestBody Product product) {
		return sellerService.changeProductDetails(sellerId, productId, product);
	}
	
}
