package com.webproject.Ecommerce.repo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.webproject.Ecommerce.entity.Customer;

@Repository
public interface CustomerRepo extends MongoRepository<Customer,String> , CustomCustomerRepo{
//	@Query("{$and :[{customerName: { $regex : ?0 }},"
//			+ "{customerPhone: { $regex : ?1 }},"
//			+ "{customerEmail: { $regex : ?2 }},"
//			+ "{customerAge :  { $gte: ?3, $lte: ?4}  },"
//			+ "{'customerAddresses.city': { $regex : ?5 }},"
//			+ "{'customerAddresses.state': { $regex : ?6 }},"
//			+ "{'customerAddresses.country': { $regex : ?7 }},"
//			+ "{'customerAddresses.pinCode': { $regex : ?8 }},"
//			+ "{'customerAddresses.addressType': { $regex : ?9 }},"
//			+ "{customerStatus: { $regex : ?10 }}]}")
//	Page<Customer> findByFilter(String name,
//			String phone,String email,
//			int startAge,int endEge,
//			String city,String state,
//			String country,
//			String pinCode,
//			String addressType,
//			String status,
//			Pageable pageable);	
}
