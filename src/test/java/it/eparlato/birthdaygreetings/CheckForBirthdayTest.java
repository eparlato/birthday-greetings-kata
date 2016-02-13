package it.eparlato.birthdaygreetings;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class CheckForBirthdayTest {

	
	@Test
	public void todayIsTheBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = Utils.toDate("03/02/1968");
		Date today = Utils.toDate("03/02/2016");

		Employee employee = new Employee(Utils.WHATEVER, Utils.WHATEVER, employeeDateOfBirth, Utils.WHATEVER);

		assertTrue(employee.isBirthday(today));
	}

	@Test
	public void todayIsNotTheBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = Utils.toDate("02/02/1982");
		Date today = Utils.toDate("03/02/2016");

		Employee employee = new Employee(Utils.WHATEVER, Utils.WHATEVER, employeeDateOfBirth, Utils.WHATEVER);

		assertFalse(employee.isBirthday(today));
	}

	@Test
	public void todayTooIsNotTheBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = Utils.toDate("03/01/1982");
		Date today = Utils.toDate("03/02/2016");

		Employee employee = new Employee(Utils.WHATEVER, Utils.WHATEVER, employeeDateOfBirth, Utils.WHATEVER);

		assertFalse(employee.isBirthday(today));
	}

	@Test
	public void todayIsBirthdayOfEmployeeWhoseBirthYearIsALeapYear() throws Exception {
		Date employeeDateOfBirth = Utils.toDate("15/01/2000");
		Date today = Utils.toDate("15/01/2016");

		Employee employee = new Employee(Utils.WHATEVER, Utils.WHATEVER, employeeDateOfBirth, Utils.WHATEVER);

		assertTrue(employee.isBirthday(today));
	}

}
