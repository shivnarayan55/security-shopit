package com.org.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.security.model.UserVerify;
import com.org.security.model.VerificationToken;



public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
	 List<VerificationToken> findByUserEmail(String email);
	    List<VerificationToken> findByToken(String token);

}
