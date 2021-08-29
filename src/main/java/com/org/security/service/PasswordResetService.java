package com.org.security.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.security.model.UserPass;
import com.org.security.repository.UserPassRepository;



@Service
public class PasswordResetService {
	

	@Autowired
	public SendingMailService sendingMailService;
	
	@Autowired
	UserPassRepository userPassRepository;


	public Object generatePasswordResetURL(String email) {

		String randomToken = UUID.randomUUID().toString();
		String passwordToken = randomToken.substring(0, randomToken.indexOf("-"));

		UserPass userPass = new UserPass();
		
		
		UserPass fetchOldRecord = new UserPass();
		fetchOldRecord = userPassRepository.findByEmail(email);
		UserPass returnObject = new UserPass();
		
		if(fetchOldRecord == null) { // OLD RECORD IS NULL | NEW USER
			userPass.setEmail(email);
			userPass.setResetToken(passwordToken);
			userPassRepository.save(userPass);
			sendingMailService.sendPasswordResetEmail(email, passwordToken);
			return userPass;
		}
		else{
			//OLD USER | FIND OLD TOKEN && CHECK IF EMAIL IS SAME
			String oldToken = fetchOldRecord.getResetToken();
					if(fetchOldRecord.getEmail().equals(email) && oldToken != passwordToken) {
							fetchOldRecord.setResetToken(passwordToken);
							userPassRepository.save(fetchOldRecord);
							sendingMailService.sendPasswordResetEmail(email, passwordToken);
		        returnObject = fetchOldRecord;
				}
					System.out.print(returnObject);
					return returnObject;
		}
	}


	


	public String savePassword(UserPass userPass) {
		userPassRepository.save(userPass);
		return "Password Updated";
	}

}

