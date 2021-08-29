
package com.org.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecurityModuleOauth2Application {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityModuleOauth2Application.class, args);
		
		System.out.println("============================================================");
		System.out.println("OAUTH2 - Security  | SHOP-IT | [RUNNING on PORT 8080]");
		System.out.println("============================================================");
		
	}

}
