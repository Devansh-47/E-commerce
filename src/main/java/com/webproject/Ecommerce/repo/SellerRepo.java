package com.webproject.Ecommerce.repo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.entity.Seller;

@Repository
public interface SellerRepo extends MongoRepository<Seller,String>,CustomSellerRepo{
	
}
