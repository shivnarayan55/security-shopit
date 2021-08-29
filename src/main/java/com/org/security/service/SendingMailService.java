package com.org.security.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.org.security.model.MailProperties;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class SendingMailService {
    private final MailProperties mailProperties;
    private final Configuration templates;

    @Autowired
    SendingMailService(MailProperties mailProperties, Configuration templates){
        this.mailProperties = mailProperties;
        this.templates = templates;
    }

    public boolean sendVerificationMail(String toEmail, String verificationCode) {
        String subject = "Please verify your email";
        String body = "";
        try {
            Template t = templates.getTemplate("email-verification.ftl");
            Map<String, String> map = new HashMap<>();
            map.put("VERIFICATION_URL", mailProperties.getVerificationapi() + verificationCode);
            body = FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return sendMail(toEmail, subject, body);
    }
    
    public boolean sendPasswordResetEmail(String toEmail, String invitationCode) {
        String subject = "ShopIT | Forgot password?";
        String body = "Hey there,<br/>\r\n"
        		+ "\r\n"
        		+ "Did you forget your password? <br/>\r\n"
        		+ "Enter the code below to reset your password: \r\n"
        		+ "<b>" + invitationCode + "</b>" +"\r\n"
        		+"If you did not request a password reset. please discard this email and write to us at  \r\n"
        		+ "support@hyperfounders.com \r\n"
        		+ "[   HyperFounder Team   ]";
        
        return sendMail(toEmail, subject, body);
    }

    private boolean sendMail(String toEmail, String subject, String body) {
        try {
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", mailProperties.getSmtp().getPort());
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailProperties.getFrom(), mailProperties.getFromName()));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            Transport transport = session.getTransport();
            transport.connect(mailProperties.getSmtp().getHost(), mailProperties.getSmtp().getUsername(), mailProperties.getSmtp().getPassword());
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return false;
    }
}
