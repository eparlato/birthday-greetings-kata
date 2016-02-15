package it.eparlato.birthdaygreetings;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface EmployeeRepository {
	public List<Employee> getEmployeesWhoseBirthadyIs(Date today);
}