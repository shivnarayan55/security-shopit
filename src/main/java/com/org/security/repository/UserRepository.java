package com.org.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.security.model.User;
import com.org.security.model.UserPass;
import com.org.security.request.UpdateUserRequest;






@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
    User findByEmail(String email);
	void save(UserPass userPass);
//	Object saveUserRequest(UpdateUserRequest user);
	
	
    
    

}