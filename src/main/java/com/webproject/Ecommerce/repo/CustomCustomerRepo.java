package com.webproject.Ecommerce.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Seller;

public interface CustomCustomerRepo {
	Page<Customer> getBy(String name,
			String phone,String email,
			Integer age,
			String city,String state,
			String country,
			String pinCode,
			String addressType,
			String status
		,Pageable page);
}
