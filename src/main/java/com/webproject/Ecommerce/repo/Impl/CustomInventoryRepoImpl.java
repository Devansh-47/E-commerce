package com.webproject.Ecommerce.repo.Impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DBRef;

import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.bo.Availibility;
import com.webproject.Ecommerce.entity.Inventory;

import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.repo.CustomInventoryRepo;

public class CustomInventoryRepoImpl implements CustomInventoryRepo {

	
	 @Autowired
	 MongoTemplate mongoTemplate;
	 
		@Override
		public void markProductStatus(String inventoryid,String productid,Availibility productStatus) {
//			Update update = new Update();
//			Query query1 = new Query(Criteria.where("inventoryId").is(inventoryid));			 
//			Inventory invnetory=mongoTemplate.findOne(query1, Inventory.class);
//			List<ProductDetails> listProductDetails=invnetory.getProducts();
//			for (ProductDetails productDetails : listProductDetails) {
//				if(productDetails.getProductId().equals(productid)) {
//					productDetails.setStatus(Availibility.UNAVAILABLE);
//				}
//			}
//			invnetory.setProducts(listProductDetails);
//			mongoTemplate.save(invnetory);
		}
	 
	 
	@Override
	public List<Inventory> getBy(String inventoryId, String productid, Availibility status) {
		  Query query = new Query();
//	     query.fields().include("id").include("name");
	      List<Criteria> criteria = new ArrayList<>();
	     if (inventoryId != null && !inventoryId.isEmpty())
	     criteria.add(Criteria.where("inventoryId").is(inventoryId));
	     if (productid != null && !productid.isEmpty())
	     criteria.add(Criteria.where("products.productId").is(productid));
	     if (status != null)
	     criteria.add(Criteria.where("products.status").is(status)); 
	if (!criteria.isEmpty())
	query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
	return mongoTemplate.find(query, Inventory.class);
	}


	@Override
	public void addProductQty(String inventoryid, String productid, int qty) {
//		Update update = new Update();
//		Query query1 = new Query(Criteria.where("inventoryId").is(inventoryid));			 
//		Inventory invnetory=mongoTemplate.findOne(query1, Inventory.class);
//		List<ProductDetails> listProductDetails=invnetory.getProducts();
//		for (ProductDetails productDetails : listProductDetails) {
//			if(productDetails.getProductId().equals(productid)) {
//				productDetails.setQuantity(qty);
//			}
//		}
//		invnetory.setProducts(listProductDetails);
//		mongoTemplate.save(invnetory);
	}

}
