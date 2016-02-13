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

}
