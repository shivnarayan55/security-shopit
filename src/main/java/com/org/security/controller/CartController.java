package com.org.security.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.org.security.dto.Cartdto;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")

public class CartController {
	
	@Autowired
	RestTemplate restTemplate;
	
	String baseUrl = "http://Shopitproducts-env.eba-pqdd3ppe.ap-south-1.elasticbeanstalk.com";
	
	@GetMapping("/{userId}")
	public List<Cartdto> viewCartByUserId(@PathVariable int userId) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		 ResponseEntity<List> responseEntity = restTemplate.exchange(baseUrl+"/cart/" + userId, HttpMethod.GET, entity, List.class);
		 return responseEntity.getBody();
		
		
	}
	
	@GetMapping("/viewAllItemsInCart")
	public List<Cartdto> viewAllItemsInCart() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		 ResponseEntity<List> responseEntity = restTemplate.exchange(baseUrl+"/cart/viewAllItemsInCart" , HttpMethod.GET, entity, List.class);
		 return responseEntity.getBody();
		
	}
	
	@PostMapping("/save")
	public  Cartdto addToCart(@RequestBody Cartdto cartdto) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<Cartdto> entity = new HttpEntity<Cartdto>(cartdto,headers);
		 ResponseEntity<Cartdto> responseEntity = restTemplate.exchange(baseUrl + "/cart/add" , HttpMethod.POST, entity, Cartdto.class);
		 return responseEntity.getBody();
		
		
	}
	@Transactional
	@DeleteMapping("/delete/{userId}")
	public  Cartdto deleteFromCartById(@PathVariable int userId) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<Cartdto> entity = new HttpEntity<Cartdto>(headers);
		 ResponseEntity<Cartdto> responseEntity = restTemplate.exchange(baseUrl + "/cart/" + userId , HttpMethod.DELETE, entity, Cartdto.class);
		 return responseEntity.getBody();
		
		
	}
	
	@Transactional
	@DeleteMapping("/deleteByProductId/{productId}")
	public  Cartdto deleteFromCartByProductId(@PathVariable int productId) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		HttpEntity<Cartdto> entity = new HttpEntity<Cartdto>(headers);
		 ResponseEntity<Cartdto> responseEntity = restTemplate.exchange(baseUrl + "/cart/deleteByProductId/" + productId , HttpMethod.DELETE, entity, Cartdto.class);
		 return responseEntity.getBody();
		
		
	}
	
	

}
