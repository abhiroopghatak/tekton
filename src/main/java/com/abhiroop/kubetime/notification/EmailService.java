package com.abhiroop.kubetime.notification;

import javax.annotation.PostConstruct;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	private static JavaMailSender javaMailSender;

	@Autowired
	private JavaMailSender jms;

	@PostConstruct
	public void init() {
		System.out.println("Inside Init method of EmailService");
		this.javaMailSender = jms;
		
		System.out.println("init method set static jms");
	}

	public static void emailNotification(String userMail, String sub, String message) {

		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userMail);
		mailMessage.setSubject("---KubeTime---" + sub);
		mailMessage.setFrom("no-reply@kubetime.com");
		mailMessage.setText("<p>" + message + "</p>");

		sendEmail(mailMessage);
	}

	@Async
	static void sendEmail(SimpleMailMessage email) {
		try {
			javaMailSender.send(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
