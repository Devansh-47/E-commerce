package com.webproject.Ecommerce.repo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Inventory;
import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.entity.Product;

@Repository
public interface InventoryRepo extends MongoRepository<Inventory,String> ,CustomInventoryRepo{
	
}
