package com.webproject.Ecommerce.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webproject.Ecommerce.bo.Availibility;
import com.webproject.Ecommerce.bo.Category;
import com.webproject.Ecommerce.bo.InventoryproductDetails;
import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Inventory;

import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.entity.Product;

import com.webproject.Ecommerce.repo.CustomerRepo;
import com.webproject.Ecommerce.repo.InventoryRepo;
import com.webproject.Ecommerce.repo.ProductRepo;
import com.webproject.Ecommerce.service.CustomerService;
import com.webproject.Ecommerce.service.InventoryService;


@Service
public class InventoryServiceImpl implements InventoryService{

	
	@Autowired
	InventoryRepo inventoryRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<InventoryproductDetails> getFilter(String productId, Category Category, String subCategory, String brandName,
			String productModelName) {
		
		Query query = new Query(); 
	      List<Criteria> criteria = new ArrayList<>();
	     if (productId != null && !productId.isEmpty())
	    	 criteria.add(Criteria.where("productId").is(productId));
	     if (Category != null )
	    	 criteria.add(Criteria.where("productCategory").is(Category));
	     if (subCategory != null && !subCategory.isEmpty())
	    	 criteria.add(Criteria.where("subCategory").is(subCategory));
	     if (brandName != null && !brandName.isEmpty())
	    	 criteria.add(Criteria.where("productBrandName").is(brandName));
	     if (productModelName != null && !productModelName.isEmpty())
	    	 criteria.add(Criteria.where("productModelName").is(productModelName));
	     
	if (!criteria.isEmpty())
		query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
	List<Product> productList=mongoTemplate.find(query, Product.class);
	
	List<InventoryproductDetails> inventoryproductDetailsList=new ArrayList<>();
	InventoryproductDetails inventoryproductDetails=new InventoryproductDetails();
	
	for (Product product : productList) {
		Inventory inventory=mongoTemplate.findById(product.getProductId(), Inventory.class);
		inventoryproductDetails.setProductCategory(product.getProductCategory());
		inventoryproductDetails.setAvailibility(inventory.getStatus());
		inventoryproductDetails.setProductModelName(product.getProductModelName());
		inventoryproductDetails.setProductQty(inventory.getQuantity());
		inventoryproductDetails.setProductSubCategory(product.getProductSubCategory());
		inventoryproductDetails.setProductId(product.getProductId());
		inventoryproductDetails.setProductSellerQtyPriceDetails(inventory.getProductSellerQtyPriceDetails());
		inventoryproductDetails.setProductBrandName(product.getProductBrandName());
		
		inventoryproductDetailsList.add(inventoryproductDetails);
		
		
	}
	
	return inventoryproductDetailsList;
	}

	@Override
	public void changeAvailabilityStatus(String productId, Availibility availibility) {
		Inventory inventory=mongoTemplate.findById(productId,Inventory.class);
		inventory.setStatus(availibility);
		mongoTemplate.save(inventory,"inventory");
	}




	

}
