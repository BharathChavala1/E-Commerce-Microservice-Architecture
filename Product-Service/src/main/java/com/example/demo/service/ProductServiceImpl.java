package com.example.demo.service;

import com.example.demo.DTO.ProductResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ProductRequest;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepo;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	public ProductRepo productRepo;

	@Override
	public String createProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		String value;
		org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
		logger.error("productServiceImpl Entered");

		Product product = Product.builder().productId(productRequest.getProductId())
				.productName(productRequest.getProductName()).productDescription(productRequest.getProductDescription())
				.productPrice(productRequest.getProductPrice()).build();
		Product status = productRepo.save(product);

        value = "Successfully created";
        return value;

    }
	public List<ProductResponse> getAllProducts(){
		List<Product> products = productRepo.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.productId(product.getProductId())
				.productDescription(product.getProductDescription())
				.productName(product.getProductName())
				.productPrice(BigDecimal.valueOf(product.getProductPrice()))
				.build();
	}
}
