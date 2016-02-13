package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateGreetingsFromEmployeeTest {


	@Test
	public void messageDependsOnEmployeeName() throws Exception {
		Greetings greetings = new Greetings(
				new Employee(Utils.WHATEVER_STRING, "Carlo", Utils.toDate("13/10/1984"), Utils.WHATEVER_STRING));
		
		assertEquals("Happy birthday, dear Carlo!", greetings.getMessage());
	}
	
	@Test
	public void anotherMessageThatDependsOnEmployeeName() throws Exception {
		
		Greetings greetings = new Greetings(
				new Employee(Utils.WHATEVER_STRING, "Ugo", Utils.toDate("13/10/1984"), Utils.WHATEVER_STRING));
		
		assertEquals("Happy birthday, dear Ugo!", greetings.getMessage());
	}
	
	@Test
	public void greetingsDestinationIsTheEmailAddressOfTheEmployee() throws Exception {
		
		Greetings greetings = new Greetings(
				new Employee(Utils.WHATEVER_STRING, Utils.WHATEVER_STRING, Utils.toDate("12/11/1981"), "diego.catellani@megaditta.it"));
		
		assertEquals("diego.catellani@megaditta.it", greetings.getDestination());
	}
	
	public class Greetings {

		private Employee employee;

		public Greetings(Employee employee) {
			this.employee = employee;
		}

		public String getDestination() {
			return employee.getEmail();
		}

		public String getMessage() {
			return String.format("Happy birthday, dear %s!", employee.getFirstName());
		}

	}

}
