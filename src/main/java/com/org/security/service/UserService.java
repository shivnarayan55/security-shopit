package com.org.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.org.security.model.User;
import com.org.security.model.UserRole;
import com.org.security.request.UpdateUserRequest;

@Service
public interface UserService {
	
	

		public List<User> getAllUsers();

//		public User addUser(User newUser);
//
//		public String deleteUser(int id);
//
		public User updateUser(UpdateUserRequest updateUserRequest);
//		
//		public List<UserRole> viewAllUserRoles();
//		
//		public User viewUserById(Long id);

		public String deleteById(Long id);
		public List<UserRole> viewAllUserRoles();
		public User viewUserById(Long id);


	

}
