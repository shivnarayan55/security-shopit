package com.org.security.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.security.model.UserVerify;
import com.org.security.model.VerificationToken;
import com.org.security.repository.UserVerifyRepository;
import com.org.security.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {
    private UserVerifyRepository userVerifyRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private SendingMailService sendingMailService;

    @Autowired
    public VerificationTokenService(UserVerifyRepository userVerifyRepository, VerificationTokenRepository verificationTokenRepository, SendingMailService sendingMailService){
        this.userVerifyRepository = userVerifyRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.sendingMailService = sendingMailService;
    }

    public Object createVerification(String email){
        List<UserVerify> users = userVerifyRepository.findByEmail(email);
        UserVerify user;
        if (users.isEmpty()) {
            user = new UserVerify();
            user.setEmail(email);
            
            userVerifyRepository.save(user);
        } else {
            user = users.get(0);
        }

        List<VerificationToken> verificationTokens = verificationTokenRepository.findByUserEmail(email);
        VerificationToken verificationToken;
        if (verificationTokens.isEmpty()) {
            verificationToken = new VerificationToken();
            verificationToken.setUserEmail(email);
            verificationToken.setUserverify(user);
            verificationTokenRepository.save(verificationToken);
            
        } else {
            verificationToken = verificationTokens.get(0);
        }

        sendingMailService.sendVerificationMail(email, verificationToken.getToken());
        return verificationToken;
    }

    public ResponseEntity<String> verifyEmail(String token){
        List<VerificationToken> verificationTokens = verificationTokenRepository.findByToken(token);
        if (verificationTokens.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }

        VerificationToken verificationToken = verificationTokens.get(0);
        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.unprocessableEntity().body("Expired token.");
        }

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
        verificationToken.getUserverify().setIsActive(true);
        verificationTokenRepository.save(verificationToken);
        
       

        return ResponseEntity.ok("You have successfully verified your email address.");
    }
}
