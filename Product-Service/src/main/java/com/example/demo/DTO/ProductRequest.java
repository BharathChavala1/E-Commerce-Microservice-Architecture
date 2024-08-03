package com.example.demo.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ProductRequest {

	private String productId;
	private String productName;
	private String productDescription;
	private double productPrice;
}
