package com.webproject.Ecommerce.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webproject.Ecommerce.bo.Category;
import com.webproject.Ecommerce.bo.Review;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Document(collection="product")
@Data
public class Product {
	@Id
	private String productId;
	
	@JsonProperty("name")
	private String productModelName;
	

	@JsonProperty("price")
	@Min(0)
	private Double productPrice;
	
	
	@JsonProperty("category")
	private Category productCategory;
	
	

	@JsonProperty("subCategory")
	private String productSubCategory;

	@JsonProperty("brandName")
	private String productBrandName;
	
	

	@JsonProperty("weight")
	private Double productweight;
	

	@JsonProperty("Dimension")
	private String productDimension;


	@JsonProperty("about")
	private String productAbout;
	
	@JsonProperty("reviewlist")
	private List<Review> reviewlist;
	
	private String adminSeller;
	
}
