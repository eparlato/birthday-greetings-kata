package it.eparlato.birthdaygreetings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class InMemoryRetrieveEmployeesWhoseBirthdayIsTest {

	private String anyString() {
		return Utils.WHATEVER_STRING;
	}
	
	private Date toDate(String dateAsString) throws ParseException {
		return Utils.toDate(dateAsString);
	}
	
	@Test
	public void noEmployeesWhoseBirthdayIsToday() throws Exception {

		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository(
				Collections.<Employee> emptyList());

		List<Employee> employees = inMemoryEmployeeRepository.getEmployeesWhoseBirthadyIs(toDate("31/12/2015"));

		assertTrue(employees.isEmpty());
	}

	@Test
	public void oneEmployeeWhoseBirthdayIsToday() throws Exception {
		Employee employee = new Employee(anyString(), anyString(), toDate("31/12/1976"), anyString());

		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository(
				Collections.singletonList(employee));

		List<Employee> employees = inMemoryEmployeeRepository.getEmployeesWhoseBirthadyIs(toDate("31/12/2015"));

		assertEquals(employee, employees.get(0));
	}
	
	@Test
	public void aFewEmployeesWhoseBirthdayIsToday() throws Exception {
		Employee employeeA = new Employee(anyString(), anyString(), toDate("15/07/1996"), anyString());
		Employee employeeB = new Employee(anyString(), anyString(), toDate("02/07/1967"), anyString());
		Employee employeeC = new Employee(anyString(), anyString(), toDate("15/07/1984"), anyString());
		Employee employeeD = new Employee(anyString(), anyString(), toDate("14/07/1974"), anyString());
		Employee employeeE = new Employee(anyString(), anyString(), toDate("15/07/1929"), anyString());

		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository(
					Arrays.asList(
							employeeA,
							employeeB,
							employeeC,
							employeeD,
							employeeE
							)
				);

		List<Employee> employees = inMemoryEmployeeRepository.getEmployeesWhoseBirthadyIs(toDate("15/07/2015"));
		
		assertTrue(employees.contains(employeeA));
		assertTrue(employees.contains(employeeC));
		assertTrue(employees.contains(employeeE));
		assertFalse(employees.contains(employeeB));
		assertFalse(employees.contains(employeeD));
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
