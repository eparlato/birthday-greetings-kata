package it.eparlato.birthdaygreetings;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

public class CheckForBirthdayTest {

	private String anyString() {
		return Utils.WHATEVER_STRING;
	}
	
	private Date toDate(String dateAsString) throws ParseException {
		return Utils.toDate(dateAsString);
	}
	
	
	@Test
	public void todayIsTheBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = toDate("03/02/1968");
		Date today = toDate("03/02/2016");

		Employee employee = new Employee(anyString(), anyString(), employeeDateOfBirth, anyString());

		assertTrue(employee.isBirthday(today));
	}

	@Test
	public void todayIsNotTheBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = toDate("02/02/1982");
		Date today = toDate("03/02/2016");

		Employee employee = new Employee(anyString(), anyString(), employeeDateOfBirth, anyString());

		assertFalse(employee.isBirthday(today));
	}

	@Test
	public void todayTooIsNotTheBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = toDate("03/01/1982");
		Date today = toDate("03/02/2016");

		Employee employee = new Employee(anyString(), anyString(), employeeDateOfBirth, anyString());

		assertFalse(employee.isBirthday(today));
	}

	@Test
	public void todayIsBirthdayOfEmployeeWhoseBirthYearIsALeapYear() throws Exception {
		Date employeeDateOfBirth = toDate("15/01/2000");
		Date today = toDate("15/01/2016");

		Employee employee = new Employee(anyString(), anyString(), employeeDateOfBirth, anyString());

		assertTrue(employee.isBirthday(today));
	}

}
