package com.webproject.Ecommerce.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webproject.Ecommerce.bo.Status;
import com.webproject.Ecommerce.entity.Customer;


import com.webproject.Ecommerce.repo.CustomerRepo;
import com.webproject.Ecommerce.service.CustomerService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	

	@Override
	public void createCustomer(String customer,MultipartFile multipartFile) {
		Customer customerObj=new Customer();
		ObjectMapper objectMapper=new ObjectMapper();
		try {			
			customerObj=objectMapper.readValue(customer,Customer.class);
			customerObj.setCustomerImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		customerRepo.save(customerObj);
		log.info("customer hass been created with  = "+customerObj.toString());
	}

	@Override
	public void softDeleteCustomerById(String id) {
		Query query1 = new Query(Criteria.where("customerId").is(id));
		Update update1 = new Update();
		update1.set("customerStatus",Status.INACTIVE);
		mongoTemplate.updateFirst(query1, update1, Customer.class);
		
	}



	@Override
	public void hardDeleteCustomerById(String id) {
		customerRepo.deleteById(id);
	}

	@Override
	public void createAllCustomers(List<Customer> listCustomers) {
		customerRepo.saveAll(listCustomers);
	}

	@Override
	public Page<Customer> findByFilter(String name,
			String phone,String email,
			Integer Age,
			String city,String state,
			String country,
			String pinCode,
			String addressType,
			String status,
			int page,
			int size,
			String sortBy
			){
		
		Pageable pegable=PageRequest.of(page, size, org.springframework.data.domain.Sort.by(sortBy));
		return customerRepo.getBy(name,phone,email,Age,city,state,country,pinCode,addressType,status,pegable);
		}

	@Override
	public void updateCustomer(String id,Customer customer) {	
		
		Update update = new Update();
		if(customer.getCustomerName()!=null) {
			update.set("customerName",customer.getCustomerName());
		}	
		if(customer.getCustomerPhone()!=null) {
			update.set("customerPhone",customer.getCustomerPhone());
		}
		if(customer.getCustomerEmail()!=null) {
			update.set("customerEmail",customer.getCustomerEmail());
		}
		
		if(customer.getCustomerGender()!=null) {
			update.set("customerGender",customer.getCustomerGender());
		}
		
		if(customer.getCustomerAge()!=0) {
			update.set("customerAge",customer.getCustomerAge());
		}
		
		if(customer.getCustomerAddresses()!=null) {
				update.set("customerAddresses",customer.getCustomerAddresses());
		}
		
	
		Query query1 = new Query(Criteria.where("customerId").is(id));
		mongoTemplate.updateFirst(query1, update, Customer.class);
	}

	@Override
	public void updateCustomerStatus(String id, Status status) {
		Update update = new Update();
		update.set("customerStatus",status);
		Query query = new Query(Criteria.where("customerId").is(id));
		mongoTemplate.updateFirst(query, update, Customer.class);
		
	}

	@Override
	public Customer findById(String customerId) {
		Optional<Customer> cust=customerRepo.findById(customerId);
		if(!cust.isEmpty()) {
			return cust.get();
			}
		else {
			return null;
		}
	}


	

}
