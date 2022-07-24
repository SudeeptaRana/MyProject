package com.rana.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	
	private String pname;
	private String Description;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getPid() {
		return pid;
	}
	
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", Description=" + Description + "]";
	}
	
	public Product(String pname, String description) {
		super();
		this.pname = pname;
		Description = description;
	}
	
	public Product() {
		super();
	}
	
	
}
