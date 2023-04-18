package com.webproject.Ecommerce.entity;

import java.util.HashMap;
import java.util.List;

import org.bson.types.Binary;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webproject.Ecommerce.bo.Address;
import com.webproject.Ecommerce.bo.CustomerMembershipType;
import com.webproject.Ecommerce.bo.Gender;
import com.webproject.Ecommerce.bo.OrderedProductDetails;
import com.webproject.Ecommerce.bo.Review;
import com.webproject.Ecommerce.bo.Status;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "customer")
@Data
@Validated
public class Customer {

	public Customer() {
		customerStatus = Status.ACTIVE;
	}

	@Id
	private String customerId;
	
	@JsonProperty("membershipType")
	private CustomerMembershipType customerMembershipType; 

	@JsonProperty("name")
	@Size(max = 30)
	@NotBlank(message = "name must not be blank")
	private String customerName;

	@JsonProperty("phone")
	@Size(max = 10, min = 10)
	@NotBlank(message = "Phone must not be blank")
	private String customerPhone;

	@JsonProperty("age")
	@Min(10)
	@Max(100)
	private int customerAge;

	@JsonProperty("gender")
	@NotNull(message = "Gender must be selected")
	private Gender customerGender;



	@Size(max = 50)
	@JsonProperty("email")
	@Email(message = "Email should be properly formated")
	private String customerEmail;

	@JsonProperty("addresses")
	private List<Address> customerAddresses;

	@JsonProperty("status")
	private Status customerStatus;
	
	 private Binary customerImage;
	
	private List<String> myOrders;
	private List<OrderedProductDetails> myCart;
	private List<OrderedProductDetails> myShoppingList;
	
	private HashMap<String,Review> myReviews;

}
