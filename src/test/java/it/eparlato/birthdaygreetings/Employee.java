package it.eparlato.birthdaygreetings;

import java.util.Date;

public class Employee {

	private Date employeeDateOfBirth;

	public Employee(Date employeeDateOfBirth) {
		this.employeeDateOfBirth = employeeDateOfBirth;
	}

	public boolean isBirthday(Date today) {
		return true;
	}

}