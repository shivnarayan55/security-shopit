package com.org.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.org.security.jwt.JwtProvider;
import com.org.security.model.Role;
import com.org.security.model.User;
import com.org.security.model.UserPass;
import com.org.security.model.UserRole;
import com.org.security.model.UserVerify;
import com.org.security.model.VerificationForm;
import com.org.security.request.LoginForm;
import com.org.security.request.SignUpForm;
import com.org.security.response.JwtResponse;
import com.org.security.response.ResponseMessage;
import com.org.security.service.PasswordResetService;
import com.org.security.service.VerificationTokenService;
import com.org.security.repository.RoleRepository;
import com.org.security.repository.UserPassRepository;
import com.org.security.repository.UserRepository;
import com.org.security.repository.UserRoleRepository;
import com.org.security.repository.UserVerifyRepository;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserVerifyRepository userVerifyRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;


	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
     VerificationTokenService verificationTokenService;
	
	@Autowired
	PasswordResetService passwordResetService;
	
	@Autowired
	UserPassRepository userPassRepository;
	

	
	

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		UserVerify userActive = new UserVerify();
		userActive = userVerifyRepository.findUserByEmail(loginRequest.getUsername());
		
		
		
		if(userActive.getIsActive().equals(true)) {
		
		Object usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		
		Authentication authentication = authenticationManager.authenticate((Authentication) usernamePasswordAuthenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);  
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		
   User user = new User();
   user = userRepository.findByEmail(loginRequest.getUsername());
  String auth = user.getAuthority();
		
		
		return ResponseEntity.ok(new JwtResponse(auth, jwt, userDetails.getUsername(), userDetails.getAuthorities()) );
		}
		else {
			return ResponseEntity.ok("Please Verify your email by clicking the link sent to you");
		} 
		} 
	

	
	@PostMapping("/signup")
	public Object registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getName())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));  // brcypting the password and holding all the details in User object.
		user.setAuthority("User");
		userRepository.save(user);  // saving the credentials of the new user
		//SETTING DEFAULT ROLE
				String defaultRole = "3";
				String userId = String.valueOf(user.getId());
				UserRole userRole = new UserRole(userId, defaultRole);
				
				userRoleRepository.save(userRole);
				
		return verificationTokenService.createVerification(signUpRequest.getEmail());  // sending a verification email to the user. 
					
	}
	


    @GetMapping("/verify-email")
    @ResponseBody
    public String verifyEmail(String code) {
    	
        return verificationTokenService.verifyEmail(code).getBody(); 
         
    }

    @PostMapping("/forgot")
    public Object generatePasswordResetURL(@RequestBody UserPass userPass) {
    	
    	UserVerify repoUser = new UserVerify(); 
    	repoUser = userVerifyRepository.findUserByEmail(userPass.getEmail()); // holds the user_verify details as an object.
    	
    	
    		if(userPass.getEmail().equals(repoUser.getEmail())) {     // checks if the input email is equal to the email in verification table.
	    		return passwordResetService.generatePasswordResetURL(userPass.getEmail()) ; // If it is equal then reset token is generate and sent to the email.
	    	}
    		
    		else {
    			return "Email does not exists!";
    		}
	    	
    		
    	

    	
    			
        	}
    @PostMapping("/resetPassword")
	public String resetPassword(@RequestBody UserPass userPass) {
    	String pass = encoder.encode(userPass.getPassword()); // turning the input password into hash(bcrypt)
	
	userPass.setPassword(pass);  // setting the password 
		
		UserPass repoUser = new UserPass();
		repoUser = userPassRepository.findByEmail(userPass.getEmail());  // getting the details of user_pass as an object

		
		if (repoUser.getResetToken().equals(userPass.getResetToken()) ) { // checking if the input token is equal to the saved token in DB.
			
		
			repoUser.setPassword(pass);
			userPassRepository.save(repoUser);
			
			User user = new User();
			user = userRepository.findByEmail(userPass.getEmail());
			user.setPassword(pass); // setting the password in the main user table.
			userRepository.save(user);   // ultimately, saving the password in the main user table.
			
			
			return "Password changed successfully";
			
		} else {
			return "Invalid Reset Token";
		}
	}
	
}