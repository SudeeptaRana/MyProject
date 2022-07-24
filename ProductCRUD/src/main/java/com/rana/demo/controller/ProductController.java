package com.rana.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rana.demo.custom.exception.BusinessException;
import com.rana.demo.custom.exception.ControllerException;
import com.rana.demo.entity.Product;
import com.rana.demo.service.ProductServiceInterface;

@Controller
@RequestMapping("/product")
public class ProductController {

//	@Autowired
//	ProductCrudRepo productRepo;
	
	@Autowired
	private ProductServiceInterface productServiceInterface;
	
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Product>> getAllProduct()
	{
		List<Product> listOfProduct = productServiceInterface.getAllProduct(); 
		return new ResponseEntity<List<Product>>(listOfProduct, HttpStatus.OK);
	}
	
	@GetMapping("/pid/{pid}")
	@ResponseBody
	public ResponseEntity<?> getProductById(@PathVariable("pid") int pid)
	{
		try {
			Product product = productServiceInterface.getProdById(pid);
			return  new ResponseEntity<Product>(product, HttpStatus.OK);
			}catch(BusinessException e){
				ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			}catch(Exception e){
				ControllerException ce = new ControllerException("611","Something wring happened in Controller class");
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			}
	}
	
	@GetMapping("/pname/{pname}")
	@ResponseBody
	public ResponseEntity<?> getProductByPname(@PathVariable("pname") String pname)
	{
		try {
			Product product = productServiceInterface.getProdByPname(pname);
			return  new ResponseEntity<Product>(product, HttpStatus.OK);
			}catch(BusinessException e){
				ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			}catch(Exception e){
				ControllerException ce = new ControllerException("611","Something wrong happened in Controller class");
				return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
			}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> addProduct(@RequestBody Product product)
	{
		try {
		Product prod = productServiceInterface.addProduct(product);
		return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
		}catch(BusinessException e){
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch(Exception e){
			ControllerException ce = new ControllerException("611","Something wrong happened in Controller class");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{pid}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("pid") int pid)
	{
		Product prod = productServiceInterface.deleteProduct(pid);
		return new ResponseEntity<Product>(prod, HttpStatus.OK);
	}
}
