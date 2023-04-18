package com.webproject.Ecommerce.bo;


import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.webproject.Ecommerce.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {
	private String cardHolderName;
	private String cardNumber;
	@JsonbDateFormat("dd/MM/yyyy")
	private Date cardExpireationDate;
	
	private String upiId;
	private String checkNo;
}
