package it.eparlato.birthdaygreetings;

import java.util.Calendar;
import java.util.Date;

public class Employee {
	private String lastName;
	private String firstName;
	private Date employeeDateOfBirth;
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
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public Date getEmployeeDateOfBirth() {
		return employeeDateOfBirth;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Employee) {
			Employee that = (Employee)obj;
			
			if(this.lastName.equals(that.getLastName()) &&
					this.firstName.equals(that.getFirstName()) && 
					this.employeeDateOfBirth.equals(that.getEmployeeDateOfBirth()) &&
					this.email.equals(that.getEmail())) {
				return true;
			}
		}
		
		return false;
	}
	

}