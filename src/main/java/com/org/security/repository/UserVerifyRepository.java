package com.org.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.org.security.model.UserVerify;

public interface UserVerifyRepository extends JpaRepository<UserVerify, String> {
	
	 List<UserVerify> findByEmail(String email);

		UserVerify findUserByEmail(String email);

}
