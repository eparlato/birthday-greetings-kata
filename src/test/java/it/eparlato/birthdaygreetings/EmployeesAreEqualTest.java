package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeesAreEqualTest {

	@Test
	public void equalIfTheyHaveTheSameLastName() throws Exception {
		Employee employeeA = new Employee("Doe", Utils.WHATEVER_STRING, Utils.toDate_yyyy_MM_dd("1980/11/06"),
				Utils.WHATEVER_STRING);
		
		Employee employeeB = new Employee("Doe", Utils.WHATEVER_STRING, Utils.toDate_yyyy_MM_dd("1980/11/06"),
				Utils.WHATEVER_STRING); 
		
		assertEquals(employeeA, employeeB);
	}
}
