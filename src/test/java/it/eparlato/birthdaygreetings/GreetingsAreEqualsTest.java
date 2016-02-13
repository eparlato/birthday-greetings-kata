package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreetingsAreEqualsTest {
	
	@Test
	public void equalsIfTheyHaveTheSameSubject() throws Exception {
		Greetings greetingsA = new Greetings(
				new Employee("Rossi", "Carlo", Utils.toDate("18/07/1985"), "carlo.rossi@megaditta.it"));
		Greetings greetingsB = new Greetings(
				new Employee("Rossi", "Carlo", Utils.toDate("18/07/1985"), "carlo.rossi@megaditta.it"));
		
		assertEquals(greetingsA, greetingsB);
	}

	@Test
	public void notEqualsIfTheyHaveDifferentMessageValues() throws Exception {
		Greetings greetingsA = new Greetings(
				new Employee("Ferrari", "Stefano", Utils.toDate("18/06/1983"), "stefano.ferrari@megaditta.it"));
		Greetings greetingsB = new Greetings(
				new Employee("Ferrari", "Giulio", Utils.toDate("18/06/1983"), "giulio.ferrari@megaditta.it"));
		
		assertNotEquals(greetingsA, greetingsB);
	}
	
	@Test
	public void notEqualsIfTheyHaveDifferentDestination() throws Exception {
		Greetings greetingsA = new Greetings(
				new Employee("Brambilla", "Luigi", Utils.toDate("20/05/1986"), "luigi.brambilla@megaditta.it"));
		Greetings greetingsB = new Greetings(
				new Employee("Brambilla", "Luigi", Utils.toDate("20/05/1986"), "luigi.brambilla01@megaditta.it"));
		
		assertNotEquals(greetingsA, greetingsB);
	}
}
