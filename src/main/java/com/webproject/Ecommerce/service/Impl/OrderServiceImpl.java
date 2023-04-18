package com.webproject.Ecommerce.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBRef;
import com.webproject.Ecommerce.bo.OrderedProductDetails;
import com.webproject.Ecommerce.bo.PaymentMethod;
import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Inventory;
import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.entity.Order;

import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.repo.CustomerRepo;
import com.webproject.Ecommerce.repo.InventoryRepo;
import com.webproject.Ecommerce.repo.OrderRepo;
import com.webproject.Ecommerce.repo.ProductRepo;
import com.webproject.Ecommerce.service.CustomerService;
import com.webproject.Ecommerce.service.InventoryService;
import com.webproject.Ecommerce.service.OrderService;


import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
	
	private static String tempOrderId=null;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	OrderRepo orderRepo;

	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
//	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  

	@Override
	public String placeOrder(String orderId,Order order) {
	
		
//		if(tempOrderId==null) {
//			return "Please Add product to Cart!!";
//		}
//		
//		Order orderDB=mongoTemplate.findById(tempOrderId, Order.class);
//		orderDB.setOrderDate(Calendar.getInstance().getTime());
//		Calendar cal=Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, 10);
//		orderDB.setEstimatedDate(cal.getTime());
//		orderDB.setActualDeliveryDate(orderDB.getEstimatedDate());
//		orderDB.setCustomerId(order.getCustomerId());
//		orderDB.setPaymentDetails(order.getPaymentDetails());
//		orderDB.setPaymentMethod(order.getPaymentMethod());
//		orderDB.setSelecteddeleveryAddress(order.getSelecteddeleveryAddress());
//		orderDB.setInventoryId(order.getInventoryId());
//		orderDB.setSellerId(order.getSellerId());
//		Double TotalBill=0.0;
//		Inventory inventory=mongoTemplate.findById(orderDB.getInventoryId(), Inventory.class);
//		List<ProductDetails> productDetailslist=inventory.getProducts();
//		
//		for (OrderedProductDetails productDetails : orderDB.getOrderedProductDetails()) {
//			String prodId=productDetails.getProductId();
////			//reducing Quantity
//			for (ProductDetails productDetail : productDetailslist) {
//				if(productDetail.getProductId().equals(prodId)) {
//					productDetail.setQuantity(productDetail.getQuantity()-productDetails.getQuantity());
//				}
//				
//				log.info("========= product id "+prodId+" Qty"+productDetail.getQuantity());
//			}
//			log.info(" product id "+prodId+" inventory id ="+orderDB.getInventoryId());
//			
//			TotalBill+=mongoTemplate.findById(prodId,Product.class).getProductPrice()*productDetails.getQuantity();
//		}
//		
//		inventory.setProducts(productDetailslist);
//		mongoTemplate.save(inventory,"Inventory");
//		
//		
//		orderDB.setBill(TotalBill);
//		mongoTemplate.save(orderDB);
//		Customer customer=mongoTemplate.findById(orderDB.getCustomerId(), Customer.class);
//		List<String> orderIds=customer.getOrderIds();
//		if(orderIds==null) {
//			orderIds=new ArrayList<String>();
//		}
//		orderIds.add(orderDB.getOrderId());
//		customer.setOrderIds(orderIds);
//		mongoTemplate.save(customer,"customer");
//		tempOrderId=null;
		return "Order Has been Succesfully Placed!!";
	}

	@Override
	public String addproductToCart(String ProductId,String sellerId,String inventoryId,Integer Qty) {
//		
//		//Status & Quantity Validation
//		Inventory inventory=mongoTemplate.findById(inventoryId, Inventory.class);
//		List<ProductDetails> productDetailslist=inventory.getProducts();
//		for (ProductDetails productDetail : productDetailslist) {
//			if(productDetail.getProductId().equals(ProductId)) {
//				if(productDetail.getQuantity()<Qty) {
//					return "Product is Out of Stock!!!";
//				}
//	
//				if(productDetail.getStatus().equals(Availibility.UNAVAILABLE)) {
//					return "Product is Unavailable!!!";
//				}
//			}
//		}
//		
//		
//		
//		Order order;
//		List<OrderedProductDetails> orderedProductDetailsList;
//		if(tempOrderId==null) {
//			order=new Order();
//			orderedProductDetailsList=new ArrayList<>();
//		}else {
//			order=mongoTemplate.findById(tempOrderId, Order.class);
//			orderedProductDetailsList=order.getOrderedProductDetails();
//		}
//		
//		OrderedProductDetails productDetails=new OrderedProductDetails();
//		productDetails.setProductId(ProductId);
//		productDetails.setSellerId(sellerId);
//		productDetails.setQuantity(Qty);
//		orderedProductDetailsList.add(productDetails);
//		order.setOrderedProductDetails(orderedProductDetailsList);
//		mongoTemplate.save(order);
//		log.info("order id ="+order.getOrderId()+" temp id = "+tempOrderId);
//		if(tempOrderId==null)
//			tempOrderId=order.getOrderId();
		
		return "Product has been added to Cart!!";
	}

	@Override
	public void removeproductToCart(String orderId, String ProductId) {
		Order orderDB=mongoTemplate.findById(tempOrderId, Order.class);
		List<OrderedProductDetails> ordereddetailsList=orderDB.getOrderedProductDetails();
		OrderedProductDetails productDetails2=null;
		for (OrderedProductDetails productDetails : ordereddetailsList) {
			if(productDetails.getProductId().equals(ProductId)) {
				productDetails2=productDetails;
			}
		}
		ordereddetailsList.remove(productDetails2);
		orderDB.setOrderedProductDetails(ordereddetailsList);
		mongoTemplate.save(orderDB,"Order");
		
	}

	

	@Override
	public void deleteOrder(String orderId) {
		orderRepo.deleteById(orderId);
	}

	@Override
	public List<Order> getOrders(String orderId, String customerId, Date orderDate, PaymentMethod paymentMethod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeOrderStatus(String orderId) {
		// TODO Auto-generated method stub
		
	}
}