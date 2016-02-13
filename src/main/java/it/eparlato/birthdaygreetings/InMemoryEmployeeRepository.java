package it.eparlato.birthdaygreetings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryEmployeeRepository implements EmployeeRepository {

	private List<Employee> employees;

	public InMemoryEmployeeRepository(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Employee> getEmployeesWhoseBirthadyIs(Date today) {
		List<Employee> employeesWhoseBirthdayIsToday = new ArrayList<Employee>();
		for (Employee employee : employees) {
			if (employee.isBirthday(today)) {
				employeesWhoseBirthdayIsToday.add(employee);
			}
		}

		return employeesWhoseBirthdayIsToday;
	}
}