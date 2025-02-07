package com.jtc.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailUtil {
	
	 private static final String SMTP_HOST = "smtp.gmail.com";
	    private static final String SMTP_PORT = "587";
	    private static final String SMTP_USER = "pdipankar832@gmail.com"; // Your email address
	    private static final String SMTP_PASSWORD = "epaz srzk qobb edtk"; // Use App password for Gmail

	    public static void sendEmail(String to, String subject, String body) {
	        // SMTP Configuration
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", SMTP_HOST);
	        props.put("mail.smtp.port", SMTP_PORT);

	        // Authenticating the email
	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(SMTP_USER, SMTP_PASSWORD);
	            }
	        });

	        try {
	            // Creating a new email message
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(SMTP_USER));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            message.setSubject(subject);

	            // Email Content (HTML Supported)
	            message.setContent(body, "text/html");

	            // Send the email
	            Transport.send(message);

	            System.out.println("✅ Email sent successfully to " + to);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            System.out.println("❌ Failed to send email to " + to);
	        }
	    }

}
