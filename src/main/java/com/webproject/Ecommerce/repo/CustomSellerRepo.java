package com.webproject.Ecommerce.repo;

import java.util.List;


import com.webproject.Ecommerce.entity.Inventory;
import com.webproject.Ecommerce.entity.Seller;



public interface CustomSellerRepo {
	List<Seller> getBy(String sellerId,String sellerName,String sellerEmail,String sellerMobile);
}
