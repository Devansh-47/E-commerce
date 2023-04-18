package com.webproject.Ecommerce.repo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product,String>,CustomProductRepo{
	
////	{'productPrice':  { $gte: ?3, $lte: ?4}},{'productId': { $regex : ?5 }}
//	@Query("{$and :[{'productName': { $regex : ?0 }},{'productCategory': { $regex : ?1 }},{'productBrandName': { $regex : ?2 }},{'productPrice':  { $gte: ?3, $lte: ?4}}]}")
//	Page<Product> findByFilter(String productName,String productCategory,String productBrandName,Double startPrice,Double endPrice,Pageable pageable);	
//
//	
//	@Query("{$and :[{'productName': { $regex : ?0 }},{'productCategory': { $regex : ?1 }},{'productBrandName': { $regex : ?2 }},{'productPrice':  { $gte: ?3, $lte: ?4}},{'productId': ?5 }}]}")
//	Page<Product> findByFilterWithId(String productName,String productCategory,String productBrandName,Double startPrice,Double endPrice,ObjectId id,Pageable pageable);	

}
