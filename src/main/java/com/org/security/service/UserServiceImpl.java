package com.org.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.security.model.User;
import com.org.security.model.UserRole;
import com.org.security.repository.UserRepository;
import com.org.security.repository.UserRoleRepository;
import com.org.security.request.UpdateUserRequest;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> user = userRepository.findAll();
		System.out.println("Test Users List:" +user);
		return user;
	}
	
//	@Override
//	public User addUser(User newUser) {
//		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
//		return userRepository.save(newUser);
//	}

//	@Override
//	public String deleteUser(int id) {
//        userRepository.deleteById((long) id);	
//        return "User Deleted";
//	}
//
	@Override
	public User updateUser(UpdateUserRequest updateUserRequest) {
UserRole userRole = new UserRole();

    System.out.println("UPDATE REQUEST--"+updateUserRequest);
		
		userRole = userRoleRepository.findByUserId(updateUserRequest.getUserId());
		System.out.println(updateUserRequest.getRoleId());
		userRole.setRoleId(updateUserRequest.getRoleId());
		
		System.out.println("USER ROLE--" +userRole);
		userRoleRepository.save(userRole);
		
		
		Long u = Long.parseLong(updateUserRequest.getUserId());
		Optional<User> user = userRepository.findById(u);
		user.get().setAuthority(updateUserRequest.getAuthority());
		user.get().setEmail(updateUserRequest.getEmail());
		user.get().setName(updateUserRequest.getName());
		user.get().setUsername(updateUserRequest.getUsername());
		System.out.println("USER---"+user);
		return userRepository.save(user.get());

	}
//
//	@Override
//	public List<UserRole> viewAllUserRoles() {
//		return userRoleRepository.findAll();
//	}
//
//	@Override
//	public User viewUserById(Long id) {
//		return userRepository.findById(id).get();
//	}

	@Override
	public String deleteById(Long id) {
		userRepository.deleteById(id);
		return "User Deleted";
		
	}
	
	@Override
	public List<UserRole> viewAllUserRoles() {
	   return userRoleRepository.findAll();
	}
	
	@Override
	public User viewUserById(Long id) {
	   return userRepository.findById(id).get();
	}

}


