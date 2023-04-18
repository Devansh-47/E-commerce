package com.webproject.Ecommerce.bo;



import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
private String customerId;
private int reviewStar;
private String reviewDescription;
private String reviewTitle;
private Date reviewDate;
private List<byte[]> reviewImages;

}
