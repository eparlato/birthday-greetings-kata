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
		return Utils.toDate_dd_MM_yyyy(dateAsString);
	}
	
	
	@Test
	public void todayIsTheBirthdayOfEmployee() throws Exception {
		Employee employee = new Employee(anyString(), anyString(), toDate("03/02/1968"), anyString());

		assertTrue(employee.isBirthday(toDate("03/02/2016")));
	}

	@Test
	public void todayIsNotTheBirthdayOfEmployee() throws Exception {
		Employee employee = new Employee(anyString(), anyString(), toDate("02/02/1982"), anyString());

		assertFalse(employee.isBirthday(toDate("03/02/2016")));
	}

	@Test
	public void todayTooIsNotTheBirthdayOfEmployee() throws Exception {
		Employee employee = new Employee(anyString(), anyString(), toDate("03/01/1982"), anyString());

		assertFalse(employee.isBirthday(toDate("03/02/2016")));
	}

	@Test
	public void todayIsBirthdayOfEmployeeWhoseBirthYearIsALeapYear() throws Exception {
		Employee employee = new Employee(anyString(), anyString(), toDate("15/01/2000"), anyString());

		assertTrue(employee.isBirthday(toDate("15/01/2016")));
	}

}
