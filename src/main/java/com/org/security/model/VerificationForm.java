package com.org.security.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class VerificationForm {
	 @NotEmpty
	    @Email
	    private String userEmail;

	    

	   

	   

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
}
