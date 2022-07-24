package com.rana.demo.service;

import java.util.List;
import java.util.Optional;

import com.rana.demo.entity.Product;

public interface ProductServiceInterface {

	public Product addProduct(Product product);

	public List<Product> getAllProduct();

	public Product getProdById(int pid);

	public Product getProdByPname(String pname);

	public Product deleteProduct(int pid);


}
