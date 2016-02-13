package it.eparlato.birthdaygreetings;

public class ConsoleMessageService implements MessageService {

	public void sendGreetings(Greetings greetings) {
		System.out.println("to: " + greetings.getDestination());
		System.out.println("subject: " + greetings.getSubject());
		System.out.println("message: " + greetings.getMessage());
		System.out.println(System.getProperty("line.separator"));
	}

}
