package com.webproject.Ecommerce.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webproject.Ecommerce.bo.Availibility;
import com.webproject.Ecommerce.bo.Category;
import com.webproject.Ecommerce.bo.InventoryproductDetails;
import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Inventory;

import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.entity.Product;


public interface InventoryService {
	
List<InventoryproductDetails> getFilter(String productId,Category Category,String subCategory,String brandName,String productModelName);
void changeAvailabilityStatus(String productId,Availibility availibility);
}
