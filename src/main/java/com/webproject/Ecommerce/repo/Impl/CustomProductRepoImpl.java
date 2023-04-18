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
import org.springframework.data.support.PageableExecutionUtils;

import com.mongodb.DBRef;

import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.repo.CustomCustomerRepo;
import com.webproject.Ecommerce.repo.CustomInventoryRepo;
import com.webproject.Ecommerce.repo.CustomProductRepo;

public class CustomProductRepoImpl implements CustomProductRepo {

	
	 @Autowired
	 MongoTemplate mongoTemplate;

	@Override
	public Page<Product> findByFilter(String productId,String productModelName, String productCategory, String productBrandName,
			Double productPrice, Double productweight, String productDimension, Pageable page) {
		
		
		Query query = new Query().with(page); 
	      List<Criteria> criteria = new ArrayList<>();
	     
	      if (productId != null && !productId.isEmpty())
		    	 criteria.add(Criteria.where("productId").is(productId));
	      if (productModelName != null && !productModelName.isEmpty())
	    	 criteria.add(Criteria.where("productModelName").is(productModelName));
	     if (productCategory != null && !productCategory.isEmpty())
	    	 criteria.add(Criteria.where("productCategory").is(productCategory));
	     if (productBrandName != null && !productBrandName.isEmpty())
	    	 criteria.add(Criteria.where("productBrandName").is(productBrandName));
	     if (productDimension != null && !productDimension.isEmpty())
	    	 criteria.add(Criteria.where("productDimension").is(productDimension));
	     if (productPrice !=null)
		     criteria.add(Criteria.where("productPrice").is(productPrice)); 
	     if (productweight !=null)
		     criteria.add(Criteria.where("productweight").is(productweight)); 
		
	     
	     if (!criteria.isEmpty())
	 		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
	 	
	 	List<Product> productList=mongoTemplate.find(query, Product.class);
	 	 return PageableExecutionUtils.getPage(
	 			productList, 
	             page, 
	             () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Product.class));
	     
	}


	 

}
