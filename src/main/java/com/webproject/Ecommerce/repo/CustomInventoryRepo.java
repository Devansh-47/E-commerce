package com.webproject.Ecommerce.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webproject.Ecommerce.bo.Availibility;
import com.webproject.Ecommerce.entity.Inventory;

import com.webproject.Ecommerce.entity.Seller;

public interface CustomInventoryRepo {
	List<Inventory> getBy(String inventoryId, String productid, Availibility satus);
	void markProductStatus(String inventoryid,String productid,Availibility productStatus);
	void addProductQty(String inventoryid,String productid,int qty);
}
