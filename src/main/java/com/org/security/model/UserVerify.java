package com.org.security.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;




@Entity
public class UserVerify {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String email;

	    private Boolean isActive;
	    
	    @OneToOne(mappedBy = "userverify", cascade = CascadeType.ALL)
	    private VerificationToken verificationToken;
	    
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

	    public Boolean getIsActive() {
	        return isActive;
	    }

	    public void setIsActive(Boolean isActive) {
	        this.isActive = isActive;
	    }


}
