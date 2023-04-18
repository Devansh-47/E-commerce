package com.webproject.Ecommerce.bo;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerProductDetails {
	private Category productCategory;
	private String subCategory;
	private String productBrandName;
	private String productModelName;
	private int productQuantity;
	private Double productPrice;
}
