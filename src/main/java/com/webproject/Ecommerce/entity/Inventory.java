package com.webproject.Ecommerce.entity;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webproject.Ecommerce.bo.Address;
import com.webproject.Ecommerce.bo.Availibility;
import com.webproject.Ecommerce.bo.ProductSellerQtyPriceDetails;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Document(collection="inventory")
@Data
public class Inventory {
	
	@Id
	private String productId; 
	private List<ProductSellerQtyPriceDetails> productSellerQtyPriceDetails;
	private int quantity;
	private Availibility status;
	
}
