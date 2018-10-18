package com.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.database.Products;

@RestController
public class ProductController {
	private static final Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value ="/products/{Id}", method =RequestMethod.GET, produces= "application/json")
	public ResponseEntity<Products> getProducts (@PathVariable int Id) {
		logger.info("Inside the getProdcut " + Id);
		
		ResponseEntity<Products> response =  null;
		
		try {

		    Products p = productService.getProducts(Id);
		
			if(p!=null) {
				response =  new ResponseEntity<Products>(p, HttpStatus.OK);
			}else {
				response = new ResponseEntity<Products>(HttpStatus.NOT_FOUND);
			}
			logger.info("returning the getProdcut " + Id);
			
		} catch(Exception e) {
			response = new ResponseEntity<Products>(HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error("Code tatti hag gaya" + e.getMessage());
		}
			return response;
	}
	
	@RequestMapping(value ="/products/{Id}", method =RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct (@PathVariable int Id) {

		ResponseEntity<Void> response =  null;
		
		productService.deleteProducts(Id);
		
		response =  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return response;
		
	}
	
	@RequestMapping(value ="/products", method =RequestMethod.POST, consumes= "application/json")
	public ResponseEntity<Products> saveProduct (@RequestBody Products p) {
		
		ResponseEntity<Products> response = null;

			int id = productService.saveProducts(p);
			
			p.setProductID(id);
			
			response =  new ResponseEntity<Products>(p, HttpStatus.CREATED);
			
			

		return response;
		
	}
	
	@RequestMapping(value ="/products/{Id}", method =RequestMethod.PUT, consumes= "application/json")
	public  ResponseEntity<Void> updateProduct (@PathVariable int Id, @RequestBody Products p) {
		
		ResponseEntity<Void> response = null;
		
			if(Id != p.getProductID()) {
				response =  new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}else {
				productService.updateProducts(p);
				response =  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}

		return response;
	}
	
	@RequestMapping(value ="/products/totalvalue", method =RequestMethod.GET, produces= "application/json")
	public ResponseEntity<Double> getProducts ( String productBrand){
		
		ResponseEntity<Double> response =  null;
		
		double price = productService.getProducts(productBrand);
		
		response =  new ResponseEntity<Double>(price, HttpStatus.OK);
				
	    return response;
	}
	
	@RequestMapping(value="/products/getMinPrice", method =RequestMethod.GET, produces= "application/json")
	public ResponseEntity<String> getMinprice(){
		
		ResponseEntity<String> response =  null;
		
		String name = productService.getMinPriceOfProduct();
		
		response =  new ResponseEntity<String>(name, HttpStatus.OK);
		
		 return response;
	}

}
