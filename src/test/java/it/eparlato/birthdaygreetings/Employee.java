package it.eparlato.birthdaygreetings;

import java.util.Calendar;
import java.util.Date;

public class Employee {

	private Date employeeDateOfBirth;
	private String lastName;
	private String firstName;
	private String email;

	public Employee(String lastName, String firstName, Date employeeDateOfBirth, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.employeeDateOfBirth = employeeDateOfBirth;
		this.email = email;
	}

	public boolean isBirthday(Date today) {
		Calendar calDateOfBirth = Calendar.getInstance();
		Calendar calToday = Calendar.getInstance();
		
		calDateOfBirth.setTime(employeeDateOfBirth);
		calToday.setTime(today);
		
		if(calDateOfBirth.get(Calendar.MONTH) == calToday.get(Calendar.MONTH)
						&& calDateOfBirth.get(Calendar.DAY_OF_MONTH) == calToday.get(Calendar.DAY_OF_MONTH)) {
			return true;
		} else {
			return false;
		}
	}
}