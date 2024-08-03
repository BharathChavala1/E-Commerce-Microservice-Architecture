package com.example.demo.DTO;

import java.math.BigDecimal;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ProductResponse {
	private String productId;
	private String productName;
	private String productDescription;
	private BigDecimal productPrice;
}
