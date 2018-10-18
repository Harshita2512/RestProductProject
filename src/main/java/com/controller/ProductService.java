 package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.ProductDatabase;
import com.database.Products;

@Service
public class ProductService {
	
	@Autowired
	ProductDatabase productDatabase;
	
    public Products getProducts(int id) {
    	
    	Products p = productDatabase.getProducts(id);
    	
    	return p;
	
}
    public void deleteProducts(int id) {
    	
    	productDatabase.deleteProduct(id);
    }
    
    public void updateProducts(Products p) {
    	
    	productDatabase.updateProduct(p);
    }
    
    public int saveProducts(Products p) {
    	
    	return productDatabase.savePersons(p);
    }
    
    public double getProducts (String productBrand){
    	
    	ArrayList<Products> list = productDatabase.getAllProducts();
    	
    	double price = 0.0;
    	for (Products p : list) {
    		if (p.getProductBrand().equals(productBrand)) {
    			price = price+(p.getProductPrice()*p.getProductQuantity());
    		}
    	}
    	return price;
    }
    
    public String getMinPriceOfProduct () {
    	
    	ArrayList<Products> list = productDatabase.getAllProducts();
    	
    	Products pro = null;
    	for(Products p : list) {
    		if(pro == null) {
    			pro =p;
    		} else if(pro.getProductPrice()>p.getProductPrice()) {
    			pro =p;
    		}
    		
    		
    	}
    	
    	return pro.getProductName();
    	
    	
    }

}
