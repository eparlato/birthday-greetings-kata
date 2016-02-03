package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class CheckForBirthdayTest {
	
	@Test
	public void todayIsBirthdayOfEmployee() throws Exception {
		Date employeeDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1982");
		Date today = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2016");
		
		Employee employee = new Employee(employeeDateOfBirth);
		
		assertTrue(employee.isBirthday(today));
	}

}
