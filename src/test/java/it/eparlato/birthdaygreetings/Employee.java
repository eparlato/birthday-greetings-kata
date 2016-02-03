package it.eparlato.birthdaygreetings;

import java.util.Calendar;
import java.util.Date;

public class Employee {

	private Date employeeDateOfBirth;

	public Employee(Date employeeDateOfBirth) {
		this.employeeDateOfBirth = employeeDateOfBirth;
	}

	public boolean isBirthday(Date today) {
		Calendar calDateOfBirth = Calendar.getInstance();
		Calendar calToday = Calendar.getInstance();
		
		calDateOfBirth.setTime(employeeDateOfBirth);
		calToday.setTime(today);
		
		if(calDateOfBirth.get(Calendar.DAY_OF_YEAR) == calToday.get(Calendar.DAY_OF_YEAR)) {
			return true;
		} else {
			return false;
		}
		
	}

}