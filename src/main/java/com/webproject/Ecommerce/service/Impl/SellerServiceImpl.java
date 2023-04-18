package com.webproject.Ecommerce.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webproject.Ecommerce.bo.Availibility;
import com.webproject.Ecommerce.bo.ProductSellerQtyPriceDetails;
import com.webproject.Ecommerce.bo.SellerCustomerOrderDetails;
import com.webproject.Ecommerce.bo.SellerProductDetails;
import com.webproject.Ecommerce.entity.Customer;
import com.webproject.Ecommerce.entity.Inventory;

import com.webproject.Ecommerce.entity.Product;
import com.webproject.Ecommerce.entity.Seller;
import com.webproject.Ecommerce.repo.CustomerRepo;
import com.webproject.Ecommerce.repo.ProductRepo;
import com.webproject.Ecommerce.repo.SellerRepo;
import com.webproject.Ecommerce.service.CustomerService;

import com.webproject.Ecommerce.service.SellerService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SellerServiceImpl implements SellerService {

	@Autowired
	SellerRepo sellerRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void createSeller(Seller seller) {
		sellerRepo.save(seller);
	}


	@Override
	public void DeleteSellerById(String id) {
		Seller seller=mongoTemplate.findById(id, Seller.class);
		for (String prodId : seller.getMyProducts()) {
			stopSellingProduct(id,prodId);
			Product product=mongoTemplate.findById(prodId, Product.class);
			if(product.getAdminSeller()==id) {
				product.setAdminSeller(null);
				}
		}
		
		sellerRepo.deleteById(id);
	}

	@Override
	public void upadateSeller(String sellerId,Seller seller) {
		Update update = new Update();
		if (seller.getSellerEmail() != null && !seller.getSellerEmail().isEmpty())
			update.set("sellerEmail", seller.getSellerEmail());
		if (seller.getSellerMobile() != null && !seller.getSellerMobile().isEmpty())
			update.set("sellerMobile", seller.getSellerMobile());
		if (seller.getSellerName() != null && !seller.getSellerName().isEmpty())
			update.set("sellerName", seller.getSellerName());
		if (seller.getSellerAddress() != null)
			update.set("sellerAddress", seller.getSellerAddress());
		Query query1 = new Query(Criteria.where("sellerId").is(sellerId));
		mongoTemplate.updateFirst(query1, update, Seller.class);
	}

	@Override
	public List<Seller> getBy(String sellerId, String sellerName, String sellerEmail, String sellerMobile) {
		return sellerRepo.getBy(sellerId, sellerName, sellerEmail, sellerMobile);
		
	}
	
	
	String checkIfProductExists(Product product) {
		List<Product> allProducts=mongoTemplate.findAll(Product.class);
		for (Product productDB : allProducts) {
			if(productDB.getProductCategory().equals(product.getProductCategory()) 
				&& productDB.getProductSubCategory().equals(product.getProductSubCategory())
				&& productDB.getProductBrandName().equals(product.getProductBrandName())
				&& productDB.getProductModelName().equals(product.getProductModelName())
					) {
				return productDB.getProductId();
			}
		}
		return null;
	}

	@Override
	public void addNewproducts(String sellerId, List<Product> productList) {
		Seller seller=mongoTemplate.findById(sellerId, Seller.class);
		List<String> productListDB;
		if(seller.getMyProducts()!=null) {
			productListDB=seller.getMyProducts();			
		}else {
			productListDB=new ArrayList<>();
			}

		//savind all products
		for (Product product : productList) {
			log.info("result ==========="+checkIfProductExists(product));
			if(checkIfProductExists(product)==null) {
				product.setAdminSeller(sellerId);
				mongoTemplate.save(product,"product");
				//saving the new  product
				productListDB.add(product.getProductId());
				Inventory inventory=new Inventory();
				inventory.setProductId(product.getProductId());
				inventory.setQuantity(0);
				List<ProductSellerQtyPriceDetails> productSellerQtyPriceDetails=new ArrayList<>();
				productSellerQtyPriceDetails.add(new ProductSellerQtyPriceDetails(sellerId,0,product.getProductPrice()));
				inventory.setStatus(Availibility.UNAVAILABLE);
				inventory.setProductSellerQtyPriceDetails(productSellerQtyPriceDetails);
				mongoTemplate.save(inventory,"inventory");
				log.info("product = "+product.getProductModelName()+" is created!!!");
				seller.setMyProducts(productListDB);
				mongoTemplate.save(seller,"seller");
				
			}else {
				List<String> prodid=new ArrayList<>();
				String existedProdId=checkIfProductExists(product);
				prodid.add(existedProdId);
				log.info("product = "+product.getProductModelName()+" was already theree!!!"+" seller id="+sellerId+" prodid="+prodid);
				addExistingproducts(sellerId,prodid);
			}
			
		}
		
	}

	@Override
	public void addExistingproducts(String sellerId,List<String> productIdsList) {
		Seller seller=mongoTemplate.findById(sellerId, Seller.class);
		List<String> productListDB;
		if(seller.getMyProducts()!=null) {
			productListDB=seller.getMyProducts();			
		}else {
			productListDB=new ArrayList<>();
			}

		for (String productId : productIdsList) {
			if(productListDB.contains(productId)) {
				log.info("Product already"+productId+" exist in seller");
				continue;
			}
			
			productListDB.add(productId);
			//add seller details to inventory 
			Inventory inventory=mongoTemplate.findById(productId, Inventory.class);
			log.info("inventory id = "+productId);
			List<ProductSellerQtyPriceDetails> productSellerQtyPriceDetails=inventory.getProductSellerQtyPriceDetails();
			Product product=mongoTemplate.findById(productId, Product.class);
			if(product.getAdminSeller()==null) {
				product.setAdminSeller(sellerId);
				}
			productSellerQtyPriceDetails.add(new ProductSellerQtyPriceDetails(sellerId,0,product.getProductPrice()));
			inventory.setProductSellerQtyPriceDetails(productSellerQtyPriceDetails);
			mongoTemplate.save(inventory,"inventory");
		}
		log.info("prod db list="+productListDB);
		seller.setMyProducts(productListDB);
		log.info("======"+seller.getSellerId());
		
		mongoTemplate.save(seller,"seller");
	}

	@Override
	public void addproductQuantity(String sellerId, String productId, int Qty) {
		if(Qty<=0) {
			return;
		}
		Inventory inventory=mongoTemplate.findById(productId,Inventory.class);
		List<ProductSellerQtyPriceDetails> productSellerQtyPriceDetailsList=inventory.getProductSellerQtyPriceDetails();
		for (ProductSellerQtyPriceDetails productSellerQtyPrice : productSellerQtyPriceDetailsList) {
			if(productSellerQtyPrice.getSellerId().equals(sellerId)) {
				productSellerQtyPrice.setQty(productSellerQtyPrice.getQty()+Qty);
				inventory.setQuantity(inventory.getQuantity()+Qty);
				if(inventory.getQuantity()>0) {
					inventory.setStatus(Availibility.AVAILABLE);
				}
			}
		}
		inventory.setProductSellerQtyPriceDetails(productSellerQtyPriceDetailsList);
		mongoTemplate.save(inventory,"inventory");
		
	}

	@Override
	public void updateProductPrice(String sellerId, String productId, Double price) {
		Inventory inventory=mongoTemplate.findById(productId,Inventory.class);
		List<ProductSellerQtyPriceDetails> productSellerQtyPriceDetailsList=inventory.getProductSellerQtyPriceDetails();
		for (ProductSellerQtyPriceDetails productSellerQtyPrice : productSellerQtyPriceDetailsList) {
			if(productSellerQtyPrice.getSellerId().equals(sellerId)) {
				productSellerQtyPrice.setPrice(price);
			}
		}
		inventory.setProductSellerQtyPriceDetails(productSellerQtyPriceDetailsList);
		mongoTemplate.save(inventory,"inventory");
	}


	@Override
	public void stopSellingProduct(String sellerId, String productId) {
		Seller seller=mongoTemplate.findById(sellerId,Seller.class);
		List<String> sellingproductIds=seller.getMyProducts();
		sellingproductIds.remove(productId);
		seller.setMyProducts(sellingproductIds);
		mongoTemplate.save(seller,"seller");
		
		
		Inventory inventory=mongoTemplate.findById(productId,Inventory.class);
		List<ProductSellerQtyPriceDetails> productSellerQtyPriceDetailsList=inventory.getProductSellerQtyPriceDetails();
		ProductSellerQtyPriceDetails sellerQtyPriceObj=null;
		for (ProductSellerQtyPriceDetails productSellerQtyPrice : productSellerQtyPriceDetailsList) {
			if(productSellerQtyPrice.getSellerId().equals(sellerId)) {
				sellerQtyPriceObj=productSellerQtyPrice;
			}
		}
		if(sellerQtyPriceObj!=null) {			
			productSellerQtyPriceDetailsList.remove(sellerQtyPriceObj);
			inventory.setQuantity(inventory.getQuantity()-sellerQtyPriceObj.getQty());
			if(inventory.getQuantity()==0) {
				inventory.setStatus(Availibility.UNAVAILABLE);
			}
		}
		
		mongoTemplate.save(inventory,"inventory");
		
	}


	@Override
	public List<SellerProductDetails> getSellerproducts(String sellerId) {
		Seller seller=mongoTemplate.findById(sellerId,Seller.class);
		List<String> productIds=seller.getMyProducts();
		List<SellerProductDetails> sellerProductDetailsList=new ArrayList<>();
		
		for (String productId: productIds) {
			SellerProductDetails sellerProductDetails=new SellerProductDetails();
			Product product=mongoTemplate.findById(productId,Product.class);
			Inventory inventory=mongoTemplate.findById(productId,Inventory.class);
			sellerProductDetails.setProductBrandName(product.getProductBrandName());
			sellerProductDetails.setProductCategory(product.getProductCategory());
			sellerProductDetails.setProductModelName(product.getProductModelName());
			sellerProductDetails.setSubCategory(product.getProductSubCategory());
			
			List<ProductSellerQtyPriceDetails> productSellerQtyPriceDetailsList=inventory.getProductSellerQtyPriceDetails();
			for (ProductSellerQtyPriceDetails productSellerQtyPrice : productSellerQtyPriceDetailsList) {
				if(productSellerQtyPrice.getSellerId().equals(sellerId)) {
					sellerProductDetails.setProductPrice(productSellerQtyPrice.getPrice());
					sellerProductDetails.setProductQuantity(productSellerQtyPrice.getQty());
				}
			}
			
			sellerProductDetailsList.add(sellerProductDetails);
		}		
		return sellerProductDetailsList;
	}


	@Override
	public List<SellerCustomerOrderDetails> getSellerOrders(String sellerId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String changeProductDetails(String sellerId,String productId, Product product) {
		Product productDB=mongoTemplate.findById(productId,Product.class);
		if(!productDB.getAdminSeller().equals(sellerId)) {
			return "You are not an Admin Seller of this product";
		}
		
		Update update = new Update();
		if(product.getProductAbout()!=null) {
			update.set("productAbout",product.getProductAbout());
		}
		if(product.getProductBrandName()!=null) {
			update.set("productBrandName",product.getProductBrandName());
		}
		if(product.getProductCategory()!=null) {
			update.set("productCategory",product.getProductCategory());
		}
		if(product.getProductDimension()!=null) {
			update.set("productDimension",product.getProductDimension());
		}
		if(product.getProductModelName()!=null) {
			update.set("productModelName",product.getProductModelName());
		}
		
		if(product.getProductPrice()!=null) {
			update.set("productPrice",product.getProductPrice());
		}
		
		if(product.getProductweight()!=null) {
			update.set("productweight",product.getProductweight());
		}
		
		
		if(product.getProductSubCategory()!=null) {
			update.set("productSubCategory",product.getProductSubCategory());
		}
		
		
		
		Query query1 = new Query(Criteria.where("productId").is(productId));
		mongoTemplate.updateFirst(query1, update, Product.class);
		
		
		
		return "Product successfully updated";
	}
	
	

	

}
