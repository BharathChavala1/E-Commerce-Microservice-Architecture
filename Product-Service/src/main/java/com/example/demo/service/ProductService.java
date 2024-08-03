package com.example.demo.service;

import com.example.demo.DTO.ProductRequest;
import com.example.demo.DTO.ProductResponse;
import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {

	public String createProduct(ProductRequest product);
	public List<ProductResponse> getAllProducts();
}
