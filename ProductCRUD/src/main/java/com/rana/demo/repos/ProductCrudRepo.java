package com.rana.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rana.demo.entity.Product;

@Repository
public interface ProductCrudRepo extends JpaRepository<Product, Integer>
{

	Product findByPname(String pname);

	
}
