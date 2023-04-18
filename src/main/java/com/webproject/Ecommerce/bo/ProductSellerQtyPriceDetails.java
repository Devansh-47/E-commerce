package com.webproject.Ecommerce.bo;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.entity.Seller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSellerQtyPriceDetails {
String sellerId;
private Integer Qty;
private Double price;
}
