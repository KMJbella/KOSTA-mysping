package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.repository.ProductRepository;

@Service(value = "productService")
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	public ProductService() {}

	
	public void list() {

	}
	public void view(String prodNo) {

	}
}
