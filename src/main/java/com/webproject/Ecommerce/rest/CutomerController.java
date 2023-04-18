package com.webproject.Ecommerce.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webproject.Ecommerce.bo.Status;
import com.webproject.Ecommerce.entity.Customer;

import com.webproject.Ecommerce.service.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequestMapping("/customer")
@RestController
public class CutomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/home")
	String homeScreen() {
		return "This is new E-commerce website...";
	}
	

	
	@GetMapping
	List<Customer> getAllCustomersBy(
			@RequestParam int pageno,
			@RequestParam int elements,
			@RequestParam String sortBy,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String phone,
			@RequestParam(required = false) String mail,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String state,
			@RequestParam(required = false) String country,
			@RequestParam(required = false) String status,
			@RequestParam(required = false) String pinCode,
			@RequestParam(required = false) String addressType,
			@RequestParam(required = false) Integer Age) {
		
		///(name,phone,email,age,city,state,country,status,pageable);
		System.out.println(" =======  "+name+" "+phone+" "+mail+"  "+city+" "+state+" "+country+" "+status);
		Pageable pageWithElements = PageRequest.of(pageno, elements,Sort.by(sortBy).ascending());
		Page<Customer> page=customerService.findByFilter(name,phone,mail,Age,city,state,country,pinCode,addressType,status,pageno,elements,sortBy);
		log.info("total pages "+page.getTotalPages());
		log.info("TotalElements "+page.getTotalElements());
		log.info("no of elements in current page "+page.getNumberOfElements());
		return page.getContent();
	}
	
	
	@GetMapping("/byid")
	Customer getCustomerById(@RequestParam String id) {
		return customerService.findById(id);
	}
	
	
	@PostMapping(path = "/create",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE  } )
	void createCustomer(@RequestPart String customer,@RequestPart MultipartFile customerImage) {
		customerService.createCustomer(customer,customerImage);
//		log.info("Customer Created with id="+customer.getCustomerId()+" name="+customer.getCustomerName());
	}
	
	@PostMapping("/createAll")
	void createCustomer(@RequestBody List<Customer> listOfCustomers) {
		customerService.createAllCustomers(listOfCustomers);
		log.info("Customer Created with id="+listOfCustomers.toString());
	}
	
	
	@DeleteMapping("deactive/id")
	void deleteCustomer(@RequestParam String id) {
		customerService.softDeleteCustomerById(id);
		log.info("Customer Created with id="+id+" Deleted(SOFT)!!!");
	}
	
	@DeleteMapping("hard/id")
	void hardDeleteCustomer(@RequestParam String id) {
		customerService.hardDeleteCustomerById(id);
		log.info("Customer Created with id="+id+" Deleted(HARD)!!!");
	}
	
	@PutMapping("/update")
	void updateCustomer(@RequestParam String id,@RequestBody Customer customer) {
		customerService.updateCustomer(id, customer);
		log.info("Updated rocored having id = "+id+" "+customer);
	}
	
	@PatchMapping("/status")
	void updateCustomerstatus(@RequestParam String id,@RequestParam Status status) {
		customerService.updateCustomerStatus(id, status);
		log.info("Updated rocored having id = "+id+" "+status);
	}
	
}
