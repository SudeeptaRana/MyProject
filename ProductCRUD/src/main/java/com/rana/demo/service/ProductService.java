package com.rana.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rana.demo.custom.exception.BusinessException;
import com.rana.demo.entity.Product;
import com.rana.demo.repos.ProductCrudRepo;

@Service
public class ProductService implements ProductServiceInterface{

	@Autowired
	private ProductCrudRepo productRepo;

	@Override
	public Product addProduct(Product product) {
		
		try {
			if(product.getPname().isEmpty() || product.getPname().length() == 0)
			{
				throw new BusinessException("601","Please send valid name.");
			}
			productRepo.save(product);
			return product;
		}
		catch(IllegalArgumentException e)
		{
			throw new BusinessException("602","given product is null" +e.getMessage());
		}
		catch(Exception e)
		{
			throw new BusinessException("603","Something went wrong in Service Layer" +e.getMessage());
		}

	}

	@Override
	public List<Product> getAllProduct() {
		try {
			List<Product> prodList = productRepo.findAll();
			if(prodList.isEmpty())
			{
				throw new BusinessException("604","DB is empty.");
			}
			return productRepo.findAll();
		}
		catch(Exception e)
		{
			throw new BusinessException("605","Something went wrong in Service Layer while fetching " +e.getMessage());
		}
	}

	@Override
	public Product getProdById(int pid) {
		try {
				return productRepo.findById(pid).get();
		}
		catch(IllegalArgumentException e)
		{
				throw new BusinessException("606","given product is null, please send some id to be search... " +e.getMessage());
		}
		catch(java.util.NoSuchElementException e)
		{
				throw new BusinessException("607","given product id does not exist in DB " +e.getMessage());
		}
	}

	@Override
	public Product getProdByPname(String pname) {
		
		try {
			return productRepo.findByPname(pname);
		}
		catch(IllegalArgumentException e)
		{
				throw new BusinessException("608","given product is null, please send some name to be search... " +e.getMessage());
		}
		catch(java.util.NoSuchElementException e)
		{
				throw new BusinessException("609","given product name does not exist in DB " +e.getMessage());
		}
	}

	@Override
	public Product deleteProduct(int pid) {
		Product prod = productRepo.findById(pid).get();
		productRepo.delete(prod);
		return prod;
	}
}
