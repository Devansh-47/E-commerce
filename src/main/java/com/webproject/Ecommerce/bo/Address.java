package com.webproject.Ecommerce.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

private String destinationNo;
private String addressLine1;
private String addressLine2;
private String city;
private String state;
private String pinCode;
private String country;
private AddressType addressType;
enum AddressType{
	HOME, WORK
}
}
