package com.webproject.Ecommerce.entity;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.webproject.Ecommerce.bo.Address;
import com.webproject.Ecommerce.bo.OrderStatus;
import com.webproject.Ecommerce.bo.OrderedProductDetails;
import com.webproject.Ecommerce.bo.PaymentDetails;
import com.webproject.Ecommerce.bo.PaymentMethod;

import lombok.Data;

@Document(collection="Order")
@Data
public class Order {
	@Id
	private String orderId;
	
	private String customerId;
	
	private List<OrderedProductDetails> OrderedProductDetails;
	
	private Double bill;
	
	private Date orderDate;
	
	private Date estimatedDate;
	private Date actualDeliveryDate;
	
	private Address selecteddeleveryAddress;
	

	private PaymentMethod paymentMethod;
	
	private PaymentDetails paymentDetails;
	
	private OrderStatus status;
	
}

