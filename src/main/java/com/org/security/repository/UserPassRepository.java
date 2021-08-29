package com.org.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.security.model.UserPass;

public interface UserPassRepository extends JpaRepository<UserPass, String> {
    UserPass findByEmail(String email);

	
}
