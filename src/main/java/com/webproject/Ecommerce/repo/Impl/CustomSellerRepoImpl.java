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
import com.webproject.Ecommerce.repo.CustomSellerRepo;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomSellerRepoImpl implements CustomSellerRepo {

	
	 @Autowired
	 MongoTemplate mongoTemplate;

	@Override
	public List<Seller> getBy(String sellerId, String sellerName, String sellerEmail, String sellerMobile) {
		Query query = new Query(); 
	      List<Criteria> criteria = new ArrayList<>();
	     
	      if (sellerId != null && !sellerId.isEmpty())
		    	 criteria.add(Criteria.where("sellerId").is(sellerId));
	      if (sellerName != null && !sellerName.isEmpty())
		    	 criteria.add(Criteria.where("sellerName").is(sellerName));
	      if (sellerEmail != null && !sellerEmail.isEmpty())
		    	 criteria.add(Criteria.where("sellerEmail").is(sellerEmail));
	      if (sellerMobile != null && !sellerMobile.isEmpty())
		    	 criteria.add(Criteria.where("sellerMobile").is(sellerMobile));
	    
	     if (!criteria.isEmpty())
	    	 query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
	 	
	 	return mongoTemplate.find(query, Seller.class);
	 	
}

	
}
