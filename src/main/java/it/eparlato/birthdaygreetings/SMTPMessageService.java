package it.eparlato.birthdaygreetings;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTPMessageService implements MessageService {

	private String host;
	private int port;
	private String from;

	public SMTPMessageService(String host, int port, String from) {
		this.host = host;
		this.port = port;
		this.from = from;
	}
	
	public void sendGreetings(Greetings greetings) {
		MimeMessage mail = createMail(greetings.getDestination(), greetings.getSubject(), greetings.getMessage());
		
		sendMail(mail);
	}

	private void sendMail(MimeMessage mail) {
		try {
			Transport.send(mail);
		} catch (MessagingException e) {
			logError("sendMail", e);
		}
	}

	private MimeMessage createMail(String to, String subject, String text) {
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		
		Session session = Session.getDefaultInstance(props);		
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(from));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(subject);
			
			message.setText(text);
		} catch (AddressException e) {
			logError("createMail", e);
		} catch (MessagingException e) {
			logError("createMail", e);
		}
		return message;
	}

	private void logError(String methodName, Exception e) {
		System.err.println(String.format("Error in method %s: %s", methodName, e.toString()));		
	}

}
