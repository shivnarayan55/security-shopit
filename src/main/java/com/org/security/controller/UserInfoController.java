package com.org.security.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.org.constants.PermissionURLConstants;
import com.org.security.model.User;
import com.org.security.model.UserRole;
import com.org.security.repository.UserRepository;
import com.org.security.repository.UserRoleRepository;
import com.org.security.request.UpdateUserRequest;


@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/api/users")
public class UserInfoController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;

	
	@GetMapping("/viewAll")
	@PreAuthorize("hasPermission('"+PermissionURLConstants.USER_API_SERVICE+"','"+PermissionURLConstants.VIEW+"')")
	public List<User> viewUser(){
		return userRepository.findAll();
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasPermission('"+PermissionURLConstants.USER_API_SERVICE+"','"+PermissionURLConstants.ADD+"')")
	public Object createUser(@RequestBody User user) {
		
		return userRepository.save(user);
		
   
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasPermission('"+PermissionURLConstants.USER_API_SERVICE+"','"+PermissionURLConstants.DELETE+"')")
	public String deleteUser(@PathVariable Long id) {
		
	    userRepository.deleteById(id);
		
		return "User Deleted" ;
	}
	
	@PutMapping("/editName")
	@PreAuthorize("hasPermission('"+PermissionURLConstants.USER_API_SERVICE+"','"+PermissionURLConstants.EDIT+"')")
	public Object editUser(@RequestBody User user) {
		User currUser = new User();
		currUser = userRepository.findByEmail(user.getEmail());
		currUser.setName(user.getName());
		return userRepository.save(currUser);
			
		
	}
	
	@PutMapping("/editUsername")
	@PreAuthorize("hasPermission('"+PermissionURLConstants.USER_API_SERVICE+"','"+PermissionURLConstants.EDIT+"')")
	public Object editUsername(@RequestBody User user) {
		User currUser = new User();
		currUser = userRepository.findByEmail(user.getEmail());
		currUser.setUsername(user.getUsername());
		return userRepository.save(currUser);
	}
		
	@PutMapping("/update")
	@PreAuthorize("hasPermission('"+PermissionURLConstants.USER_API_SERVICE+"','"+PermissionURLConstants.EDIT+"')")
    public User updateUser(@RequestBody UpdateUserRequest updateUserRequest )	{
		
		
		UserRole userRole = new UserRole();
		
		userRole = userRoleRepository.findByUserId(updateUserRequest.getUserId());
		userRole.setRoleId(updateUserRequest.getRoleId());
		userRoleRepository.save(userRole);
		
		Long u = Long.parseLong(updateUserRequest.getUserId());
		Optional<User> user = userRepository.findById(u);
		user.get().setAuthority(updateUserRequest.getAuthority());
		user.get().setEmail(updateUserRequest.getEmail());
		user.get().setName(updateUserRequest.getName());
		user.get().setUsername(updateUserRequest.getUsername());
		return userRepository.save(user.get());
		
						
		
		
	}
	
	
			
		
	
	

	
	
}
