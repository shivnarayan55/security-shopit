package com.org.security.request;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class SignUpForm {

    private String name;


    private String username;

    @Email
    private String email;
    
    private Set<String> role;
    
   
    private String password;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Set<String> getRole() {
		return role;
	}


	public void setRole(Set<String> role) {
		this.role = role;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

   
    
    
    
    
    
    
    
    
    
    
    
    
    
}