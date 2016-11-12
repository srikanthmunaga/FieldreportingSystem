package frs_cls;


import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 

public class EmailUtility {
	 public static void sendEmail(String host, String port,
	            final String userName, final String password, String toAddress,
	            String subject, StringBuffer content) throws AddressException,
	            MessagingException {
	     
		 
		 
		 
		 // sets SMTP server properties
		   // String host1="smtp.gmail.com";
	        
		    Properties properties = new Properties();
	        properties.put("mail.smtp.host",host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	 
	        // creates a new session with an authenticator
//	        Authenticator auth = new Authenticator() {
//	            public PasswordAuthentication getPasswordAuthentication() {
//	                return new PasswordAuthentication(userName,password);
//	            }
//	        };
//	 
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(userName, password);
	            }
	        });
	 
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	 
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        msg.setContent(content.toString(),"text/html");
	        System.out.println(content);
	 
	        // sends the e-mail
	        Transport.send(msg);
	 
	    }
}



