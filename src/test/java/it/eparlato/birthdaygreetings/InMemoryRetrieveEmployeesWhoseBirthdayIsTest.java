package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	@Test
	public void aFewEmployeesWhoseBirthdayIsToday() throws Exception {
		Employee employeeA = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("15/07/1996"));
		Employee employeeB = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("02/07/1967"));
		Employee employeeC = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("15/07/1984"));
		Employee employeeD = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("14/07/1974"));
		Employee employeeE = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("15/07/1929"));

		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository(
					Arrays.asList(
							employeeA,
							employeeB,
							employeeC,
							employeeD,
							employeeE
							)
				);

		Date today = new SimpleDateFormat("dd/MM/yyyy").parse("15/07/2015");

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
