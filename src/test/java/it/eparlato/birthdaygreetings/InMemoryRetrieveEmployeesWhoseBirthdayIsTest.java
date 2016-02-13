package it.eparlato.birthdaygreetings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class InMemoryRetrieveEmployeesWhoseBirthdayIsTest {

	private final String WHATEVER = Utils.WHATEVER;
	
	@Test
	public void noEmployeesWhoseBirthdayIsToday() throws Exception {

		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository(
				Collections.<Employee> emptyList());

		Date today = Utils.toDate("31/12/2015");

		List<Employee> employees = inMemoryEmployeeRepository.getEmployeesWhoseBirthadyIs(today);

		assertTrue(employees.isEmpty());
	}

	@Test
	public void oneEmployeeWhoseBirthdayIsToday() throws Exception {
		Employee employee = new Employee(WHATEVER, WHATEVER, Utils.toDate("31/12/1976"), WHATEVER);

		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository(
				Collections.singletonList(employee));

		Date today = Utils.toDate("31/12/2015");

		List<Employee> employees = inMemoryEmployeeRepository.getEmployeesWhoseBirthadyIs(today);

		assertEquals(employee, employees.get(0));
	}
	
	@Test
	public void aFewEmployeesWhoseBirthdayIsToday() throws Exception {
		Employee employeeA = new Employee(WHATEVER, WHATEVER, Utils.toDate("15/07/1996"), WHATEVER);
		Employee employeeB = new Employee(WHATEVER, WHATEVER, Utils.toDate("02/07/1967"), WHATEVER);
		Employee employeeC = new Employee(WHATEVER, WHATEVER, Utils.toDate("15/07/1984"), WHATEVER);
		Employee employeeD = new Employee(WHATEVER, WHATEVER, Utils.toDate("14/07/1974"), WHATEVER);
		Employee employeeE = new Employee(WHATEVER, WHATEVER, Utils.toDate("15/07/1929"), WHATEVER);

		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository(
					Arrays.asList(
							employeeA,
							employeeB,
							employeeC,
							employeeD,
							employeeE
							)
				);

		Date today = Utils.toDate("15/07/2015");

		List<Employee> employees = inMemoryEmployeeRepository.getEmployeesWhoseBirthadyIs(today);
		
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
