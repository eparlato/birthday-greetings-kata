package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class CheckForBirthdayTest {

	private final String WHATEVER = "For the purpose of the test whatever value is valid";
	
	@Test
	public void todayIsTheBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1968");
		Date today = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2016");

		Employee employee = new Employee(WHATEVER, WHATEVER, employeeDateOfBirth, WHATEVER);

		assertTrue(employee.isBirthday(today));
	}

	@Test
	public void todayIsNotTheBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("02/02/1982");
		Date today = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2016");

		Employee employee = new Employee(WHATEVER, WHATEVER, employeeDateOfBirth, WHATEVER);

		assertFalse(employee.isBirthday(today));
	}

	@Test
	public void todayTooIsNotTheBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("03/01/1982");
		Date today = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2016");

		Employee employee = new Employee(WHATEVER, WHATEVER, employeeDateOfBirth, WHATEVER);

		assertFalse(employee.isBirthday(today));
	}

	@Test
	public void todayIsBirthdayOfEmployeeWhoseBirthYearIsALeapYear() throws Exception {
		Date employeeDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("15/01/2000");
		Date today = new SimpleDateFormat("dd/MM/yyyy").parse("15/01/2016");

		Employee employee = new Employee(WHATEVER, WHATEVER, employeeDateOfBirth, WHATEVER);

		assertTrue(employee.isBirthday(today));
	}

}
