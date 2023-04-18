package com.webproject.Ecommerce.service;

import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webproject.Ecommerce.bo.SellerCustomerOrderDetails;
import com.webproject.Ecommerce.bo.SellerProductDetails;
import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Inventory;
import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.entity.Product;

public interface SellerService {
//addproduct(Existing/New)
	
	
	
void createSeller(Seller seller);
void DeleteSellerById(String id);
void upadateSeller(String sellerId,Seller seller);
void addNewproducts(String sellerId,List<Product> productList);
void addExistingproducts(String selerId,List<String> productIdsList);
List<Seller> getBy(String sellerId,String sellerName,String sellerEmail,String sellerMobile);
void addproductQuantity(String sellerId,String productId,int Qty);
void updateProductPrice(String sellerId,String productId,Double price);
void stopSellingProduct(String sellerId,String productId);
List<SellerProductDetails> getSellerproducts(String sellerId);
List<SellerCustomerOrderDetails> getSellerOrders(String sellerId);
String changeProductDetails(String sellerId,String productId,Product product);

}
