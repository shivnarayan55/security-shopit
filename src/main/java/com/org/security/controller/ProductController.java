package com.org.security.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.org.constants.PermissionURLConstants;
import com.org.security.dto.Product;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	RestTemplate restTemplate;
	
	String baseUrl = "http://Shopitproducts-env.eba-pqdd3ppe.ap-south-1.elasticbeanstalk.com";

	
	@GetMapping("/viewAll")
	@PreAuthorize("hasPermission('"+PermissionURLConstants.PRODUCT_API_SERVICE+"','"+PermissionURLConstants.VIEW+"')")
	public String viewProduct(){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl+"/viewAllproducts", HttpMethod.GET, entity, String.class);
		
		
		return responseEntity.getBody();
	}
	
	@GetMapping("/viewProduct/{id}")
    public String viewProductById(@PathVariable int id){
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<Integer> entity = new HttpEntity<Integer>(id);
		ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl+"/viewProduct/" + id, HttpMethod.GET, entity, String.class);
		
		
		return responseEntity.getBody();
	}
	
	
	
	
	
	
	@PostMapping("/add")
//	@PreAuthorize("hasPermission('"+PermissionURLConstants.PRODUCT_API_SERVICE+"','"+PermissionURLConstants.ADD+"')")
	public Object createProduct(@RequestBody Product product) {
		
		 HttpHeaders headers = new HttpHeaders();
	     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
	     
	     return restTemplate.exchange(
	    	         baseUrl + "/save", HttpMethod.POST, entity, Object.class).getBody();
       
	}
	
//	@PutMapping("/edit")
//	public String update(@RequestBody Product product) {
//		
//		 HttpHeaders headers = new HttpHeaders();
//	     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	     HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
//	     
//	     return restTemplate.exchange( baseUrl+ "/edit", HttpMethod.PUT, entity, String.class).getBody();
//		
//	}
	
	@DeleteMapping("/delete/{id}")
//	@PreAuthorize("hasPermission('"+PermissionURLConstants.PRODUCT_API_SERVICE+"','"+PermissionURLConstants.DELETE+"')")
	public String deleteProduct(@PathVariable int id) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<Integer> entity = new HttpEntity<Integer>(id);
		ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl+"/delete/" + id, HttpMethod.DELETE, entity, String.class);
		
		
		return responseEntity.getBody();
		
		
	}
	
	
}




