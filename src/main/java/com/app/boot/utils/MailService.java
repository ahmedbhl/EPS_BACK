package com.app.boot.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.app.boot.model.User;

@Service
public class MailService {

	/*
	 * The Spring Framework provides an easy abstraction for sending email by
	 * using the JavaMailSender interface, and Spring Boot provides
	 * auto-configuration for it as well as a starter module.
	 */
	private JavaMailSender javaMailSender;

	/**
	 * 
	 * @param javaMailSender
	 */
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * This function is used to send mail without attachment.
	 * 
	 * @param user
	 * @throws MailException
	 */

	public void sendEmail(User user) throws MailException {

		/*
		 * This JavaMailSender Interface is used to send Mail in Spring Boot.
		 * This JavaMailSender extends the MailSender Interface which contains
		 * send() function. SimpleMailMessage Object is required because send()
		 * function uses object of SimpleMailMessage as a Parameter
		 */

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("Testing Mail API");
		mail.setText("Hurray ! You have done that dude...");

		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		javaMailSender.send(mail);
	}

	/**
	 * This fucntion is used to send mail that contains a attachment.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 */
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmail());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}

	/**
	 * This fucntion is used to send mail with html text.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 */
	public void sendEmailRegistration(User user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmail());
		helper.setSubject("Thank you for your registration !");
		helper.setText(
				"<html> <body><div style='align-items: center;text-align:center;background-color: aliceblue;height: 270px;padding: 10px;'><h1 style=' color: darkblue; font-family: cursive;'>Hello "
						+ user.getFirstName() + "&nbsp;" + user.getLastName()
						+ " </h1><p style='font-size: large;font-family: cursive;'>Your registration has been completed.</p>"
						+ " <p style='font-size: large;font-family: cursive;'>The whole team of educational personal space welcomes you and thanks you for your trust</p>"
						+ "<h2 style='font-size: large;font-family: cursive;'>Your username : " + user.getEmail()
						+ "</h2>" + "<a href='https://eps-project-319b4.firebaseapp.com/signup?key=eps"
						+ user.getPassword().substring(0, 10) + user.getId()
						+ "' style='width:220px;border:none;background-color:#1e5bc6;border-radius:20px;color:#ffffff;display:inline-block;text-transform:uppercase;font-family:Arial,Helvetica,Geneva,sans-serif;font-size:14px;font-weight:bold;line-height:45px;text-align:center;text-decoration:none'>I activate right now</a></div></body></html>",
				true);
		javaMailSender.send(mimeMessage);
	}

	/**
	 * This fucntion is used to send mail with html text or reset Password.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 */
	public void sendResetPassword(User user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmail());
		helper.setSubject("Reset Your Educational Personal Space Password");
		helper.setText(
				"<html> <body><div style='align-items: center;text-align:center;background-color: aliceblue;height: 270px;padding: 10px;'><h1 style=' color: darkblue; font-family: cursive;'>"
						+"<h1 style='padding-top:0px;font-family:Helvetica neue,Helvetica,Arial,Verdana,sans-serif;color:#205081;font-size:20px;line-height:32px;text-align:left;font-weight:bold'>Don't worry, we all forget sometimes</h1><hr>"
						+ "	Hi " + user.getFirstName() + "&nbsp;" + user.getLastName()
						+ " </h1><p style='font-size: large;font-family: cursive;'>You've recently asked to reset the password for this eps account:</p>"
						+ "<h2 style='font-size: large;font-family: cursive;'>Your username : " + user.getEmail()
						+ "</h2>"
						+ "<p style='font-size: large;font-family: cursive;'>To update your password, click the button below:</p>"
						+ "<a href='https://eps-project-319b4.firebaseapp.com/reset?email=" + user.getEmail() + "&key="
						+ user.getPassword()
						+ "' style='width:220px;border:none;background-color:#1e5bc6;border-radius:20px;color:#ffffff;display:inline-block;text-transform:uppercase;font-family:Arial,Helvetica,Geneva,sans-serif;font-size:14px;font-weight:bold;line-height:45px;text-align:center;text-decoration:none'>Reset my password</a></div></body></html>",
				true);
		javaMailSender.send(mimeMessage);
	}

}
