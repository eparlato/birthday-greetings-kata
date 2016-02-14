package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateGreetingsFromEmployeeTest {


	@Test
	public void messageDependsOnEmployeeName() throws Exception {
		Greetings greetings = new Greetings(
				new Employee(Utils.WHATEVER_STRING, "Carlo", Utils.toDate_dd_MM_yyyy("13/10/1984"), Utils.WHATEVER_STRING));
		
		assertEquals("Happy birthday, dear Carlo!", greetings.getMessage());
	}
	
	@Test
	public void anotherMessageThatDependsOnEmployeeName() throws Exception {
		Greetings greetings = new Greetings(
				new Employee(Utils.WHATEVER_STRING, "Ugo", Utils.toDate_dd_MM_yyyy("13/10/1984"), Utils.WHATEVER_STRING));
		
		assertEquals("Happy birthday, dear Ugo!", greetings.getMessage());
	}
	
	@Test
	public void greetingsDestinationIsTheEmailAddressOfTheEmployee() throws Exception {
		Greetings greetings = new Greetings(
				new Employee(Utils.WHATEVER_STRING, Utils.WHATEVER_STRING, Utils.toDate_dd_MM_yyyy("12/11/1981"), "diego.catellani@megaditta.it"));
		
		assertEquals("diego.catellani@megaditta.it", greetings.getDestination());
	}
	
	@Test
	public void greetingsSubjectIs() throws Exception {
		Greetings greetings = new Greetings(
				new Employee(Utils.WHATEVER_STRING, Utils.WHATEVER_STRING, Utils.toDate_dd_MM_yyyy("12/11/1981"),Utils.WHATEVER_STRING));
		
		assertEquals("Happy Birthday!", greetings.getSubject());
	}

}
