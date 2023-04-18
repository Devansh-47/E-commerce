package com.webproject.Ecommerce.bo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webproject.Ecommerce.bo.Review;



import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryproductDetails {
	
	private String productId;
	private String productModelName;
	private Category productCategory;
	private String productSubCategory;
	private String productBrandName;
	private int productQty;
	private Availibility availibility;
	private List<ProductSellerQtyPriceDetails> productSellerQtyPriceDetails;
	
}
