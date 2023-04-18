package com.webproject.Ecommerce.entity;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webproject.Ecommerce.bo.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="seller")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
	@Id
	private String sellerId;
	
	
	@Size(max = 30)
	@NotBlank(message = "name must not be blank")
	private String sellerName;
	
	@Size(max = 50)
	@Email(message = "Email should be properly formated")
	private String sellerEmail;
	
	@Size(max = 10, min = 10)
	@NotBlank(message = "Phone must not be blank")
	private String sellerMobile; 
	

	private Address sellerAddress;
	
	private List<String> myProducts;
	private List<String> myOrders;
	
}
