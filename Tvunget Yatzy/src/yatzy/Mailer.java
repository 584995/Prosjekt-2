package yatzy;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	public static void send(String til,String emne,String melding){  
		  
		final String bruker="Tvunget-Yatzy-no-reply@outlook.com";  
		final String passord="qwertyui123";  
		  
		//1st step) Get the session object    
		Properties properties = new Properties();  
		properties.put("mail.smtp.host", "smtp-mail.outlook.com"); 
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		  
		Session session = Session.getDefaultInstance(properties,  
		 new javax.mail.Authenticator() {  
		  protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(bruker,passord);  
		   }  
		});  
		//2nd step)compose message  
		try {  
		 MimeMessage message = new MimeMessage(session);  
		 message.setFrom(new InternetAddress(bruker));  
		 message.addRecipient(Message.RecipientType.TO, new InternetAddress(til));  
		 message.setSubject(emne);  
		 message.setText(melding);  
		   
		 //3rd step)send message  
		 Transport.send(message);  
		  		  
		 } catch (MessagingException e) {  
		    throw new RuntimeException(e);  
		 }  
		      
		}  
}
