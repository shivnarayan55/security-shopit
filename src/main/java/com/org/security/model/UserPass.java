package com.org.security.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserPass {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	   
	    private String email;
	    
	    
	    private String resetToken;
	    private String password;
	    
	   
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getResetToken() {
			return resetToken;
		}
		public void setResetToken(String resetToken) {
			this.resetToken = resetToken;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "UserPass [id=" + id + ", email=" + email + ", resetToken=" + resetToken + ", password=" + password
					+ "]";
		}
		
		
		
		
	    
	    

}
