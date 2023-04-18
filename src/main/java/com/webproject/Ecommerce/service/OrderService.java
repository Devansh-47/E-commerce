package com.webproject.Ecommerce.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webproject.Ecommerce.bo.PaymentMethod;
import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.entity.Order;

import com.webproject.Ecommerce.entity.Product;

public interface OrderService {
String placeOrder(String orderId,Order order);
String addproductToCart(String ProductId,String sellerId,String inventoryId,Integer Qty);
void removeproductToCart(String orderId,String ProductId);
void deleteOrder(String orderId);
List<Order> getOrders(String orderId,String customerId,Date orderDate,PaymentMethod paymentMethod);
void changeOrderStatus(String orderId);
}
