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
		
		if(calDateOfBirth.get(Calendar.MONTH) == calToday.get(Calendar.MONTH)
						&& calDateOfBirth.get(Calendar.DAY_OF_MONTH) == calToday.get(Calendar.DAY_OF_MONTH)
				) {
			return true;
		} else {
			return false;
		}
		
	}

}