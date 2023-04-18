package com.webproject.Ecommerce.bo;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.webproject.Ecommerce.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductDetails {
	private String sellerId;
	private String productId;
	private int quantity;
}
