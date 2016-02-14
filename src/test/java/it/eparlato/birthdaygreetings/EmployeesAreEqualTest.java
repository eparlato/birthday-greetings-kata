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
	
	@Test
	public void notEqualIfTheyHaveDifferentFirstName() throws Exception {
		Employee employeeA = new Employee("Doe", "John", Utils.toDate_yyyy_MM_dd("1980/11/06"),
				Utils.WHATEVER_STRING);
		
		Employee employeeB = new Employee("Doe", "Carl", Utils.toDate_yyyy_MM_dd("1980/11/06"),
				Utils.WHATEVER_STRING); 
		
		assertNotEquals(employeeA, employeeB);
	}
	
	@Test
	public void notEqualIfTheyHaveDifferentDateOfBirth() throws Exception {
		Employee employeeA = new Employee("Doe", "John", Utils.toDate_yyyy_MM_dd("1980/11/06"),
				Utils.WHATEVER_STRING);
		
		Employee employeeB = new Employee("Doe", "John", Utils.toDate_yyyy_MM_dd("1980/12/06"),
				Utils.WHATEVER_STRING); 
		
		assertNotEquals(employeeA, employeeB);
	}
	
	@Test
	public void notEqualIfTheyHaveDifferentDateEmail() throws Exception {
		Employee employeeA = new Employee("Doe", "John", Utils.toDate_yyyy_MM_dd("1980/11/06"),
				"john.doe@foobar.com");
		
		Employee employeeB = new Employee("Doe", "John", Utils.toDate_yyyy_MM_dd("1980/11/06"),
				"john.doe2@foobar.com"); 
		
		assertNotEquals(employeeA, employeeB);
	}
}
