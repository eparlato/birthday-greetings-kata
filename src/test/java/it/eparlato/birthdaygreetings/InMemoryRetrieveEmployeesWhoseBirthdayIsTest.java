package it.eparlato.birthdaygreetings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class InMemoryRetrieveEmployeesWhoseBirthdayIsTest {

	@Test
	public void noEmployeesWhoseBirthdayIsToday() throws Exception {

		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository(
				Collections.<Employee> emptyList());

		Date today = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2015");

		List<Employee> employees = inMemoryEmployeeRepository.getEmployeesWhoseBirthadyIs(today);

		assertTrue(employees.isEmpty());
	}

	@Test
	public void oneEmployeeWhoseBirthdayIsToday() throws Exception {
		Employee employee = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1976"));

		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository(
				Collections.singletonList(employee));

		Date today = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2015");

		List<Employee> employees = inMemoryEmployeeRepository.getEmployeesWhoseBirthadyIs(today);

		assertEquals(employee, employees.get(0));
	}

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
}
