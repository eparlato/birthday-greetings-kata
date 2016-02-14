package it.eparlato.birthdaygreetings;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LearningMail {

	public static void main(String[] args) {
		new LearningMail().sendMail();
	}
	
	private void sendMail() {
		String to = "ed@foobar.com";
		String from = "test@eparlato.it";
		
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "localhost");
		properties.put("mail.smtp.port", "25");
		
		Session session = Session.getDefaultInstance(properties);		
		
		
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(from));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject("Happy Birthday!");
			
			message.setText("Happy Birthday, dear Ed!");
			
			System.out.println("Sending mail to " + to);
			
			Transport.send(message);
			
			System.out.println("Mail correctly sent to " + to);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
