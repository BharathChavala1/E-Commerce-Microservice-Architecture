package com.example.demo.controller;

import com.example.demo.DTO.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DTO.ProductRequest;
import com.example.demo.service.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	public ProductServiceImpl productService;

	// create product
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ResponseEntity<?> addProduct(@RequestBody ProductRequest product) {
		String productStatus = productService.createProduct(product);
		return new ResponseEntity(productStatus, HttpStatus.CREATED);
	}
	@ResponseBody
	@RequestMapping(value="/getAllProducts",method=RequestMethod.GET)
	public ResponseEntity<?> getAllProducts(){
		List<ProductResponse> products = productService.getAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}

}
