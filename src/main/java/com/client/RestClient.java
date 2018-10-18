package com.client;

import org.springframework.web.client.RestTemplate;

import com.database.Products;

public class RestClient {
	
	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
				
		Products createproducts = new Products();
		createproducts.setProductName("Glass");
		createproducts.setProductPrice(5.00);
		createproducts.setProductQuantity(60);
		createproducts.setProductBrand("tuperware");
		
		createproducts =  restTemplate.postForObject("http://localhost:8080/RestProductProject/products", createproducts, Products.class);
		
		int id = createproducts.getProductID();
		
		System.out.println(id);
		
		String url =  "http://localhost:8080/RestProductProject/products/"+id;
	    
	    Products p = restTemplate.getForObject(url, Products.class);
	
		
		
		p.setProductName("Soap");
		p.setProductPrice(6.00);
		p.setProductQuantity(4);
		restTemplate.put(url, p);
		

		restTemplate.delete(url);
		
	}

}
