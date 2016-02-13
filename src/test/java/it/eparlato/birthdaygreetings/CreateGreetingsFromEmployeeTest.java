package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateGreetingsFromEmployeeTest {


	@Test
	public void messageDependsOnEmployee() throws Exception {
		
		Greetings greetings = new Greetings(
				new Employee("Rossi", "Carlo", Utils.toDate("13/10/1984"), "carlo.rossi@megaditta.it"));
		
		assertEquals("Happy birthday, dear Carlo!", greetings.getMessage());
	}
	
	public class Greetings {

		public Greetings(Employee employee) {
			// TODO Auto-generated constructor stub
		}

		public String getMessage() {
			return "Happy birthday, dear Carlo!";
		}

	}

}
