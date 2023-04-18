package com.webproject.Ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.webproject.Ecommerce.bo.Status;
import com.webproject.Ecommerce.entity.Customer;


public interface CustomerService {
void createCustomer(String customer,MultipartFile multipartFile);
void createAllCustomers(List<Customer> listCustomers);

void softDeleteCustomerById(String id);
void hardDeleteCustomerById(String id);

void updateCustomer(String id,Customer customer);
void updateCustomerStatus(String id,Status status);


public Page<Customer> findByFilter(String name,
		String phone,String email,
		Integer Age,
		String city,String state,
		String country,
		String pinCode,
		String addressType,
		String status,int page,
		int size,
		String sortBy);	

public Customer findById(String customerId);




}
