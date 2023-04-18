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

public class CustomCustomerRepoImpl implements CustomCustomerRepo {

	
	 @Autowired
	 MongoTemplate mongoTemplate;

	@Override
	public Page<Customer> getBy(String name, String phone, String email, Integer age, String city,
		String state, String country, String pinCode, String addressType, String status,Pageable page) {
		Query query = new Query().with(page); 
	      List<Criteria> criteria = new ArrayList<>();
	     if (name != null && !name.isEmpty())
	    	 criteria.add(Criteria.where("customerName").is(name));
	     if (phone != null && !phone.isEmpty())
	    	 criteria.add(Criteria.where("customerPhone").is(phone));
	     if (email != null && !email.isEmpty())
	    	 criteria.add(Criteria.where("customerEmail").is(email)); 
	     if (age !=null)
		     criteria.add(Criteria.where("customerAge").is(age)); 
	     if (city != null && !city.isEmpty())
		     criteria.add(Criteria.where("customerAddresses.city").is(city));
	     if (state != null && !state.isEmpty())
		     criteria.add(Criteria.where("customerAddresses.state").is(state));
	     if (country != null && !country.isEmpty())
		     criteria.add(Criteria.where("customerAddresses.country").is(country));
	     if (pinCode != null && !pinCode.isEmpty())
		     criteria.add(Criteria.where("customerAddresses.pinCode").is(pinCode));
	     if (status != null && !status.isEmpty())
		     criteria.add(Criteria.where("customerStatus").is(status));
	     
	     
	if (!criteria.isEmpty())
		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
	
	List<Customer> custList=mongoTemplate.find(query, Customer.class);
	 return PageableExecutionUtils.getPage(
			 custList, 
            page, 
            () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Customer.class));
	}
	 
	 
	 
	 

}
