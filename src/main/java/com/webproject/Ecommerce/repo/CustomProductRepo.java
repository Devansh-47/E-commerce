package com.webproject.Ecommerce.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.entity.Seller;

public interface CustomProductRepo {
	Page<Product> findByFilter(
			String productId,
			String productModelName,
			String productCategory,
			String productBrandName,
			Double price,
			Double productweight,
			String productDimension,
			Pageable pageable);
}
