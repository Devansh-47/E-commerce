package com.webproject.Ecommerce.bo;

import java.util.Date;
import java.util.HashMap;

public class SellerCustomerOrderDetails {
private String customerName;
private String customerPhone;
private String customerEmail;
HashMap<String,Integer> orderDetails;//productModel Name , Quantity
private Double bill;
private Date orderDate;
private Date actualDeliveryDate;

private Address selecteddeleveryAddress;
private PaymentMethod paymentMethod;
private OrderStatus status;

}
