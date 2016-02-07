package it.eparlato.birthdaygreetings;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class InMemoryRetrieveEmployeesWhoseBirthdayIsTest {


	@Test
	public void noEmployeesWhoseBirthdayIsToday() throws Exception {
		
		InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository();
		
		Date today = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2015");
		
		List<Employee> employees = inMemoryEmployeeRepository.getEmployeesWhoseBirthadyIs(today);
		
		assertTrue(employees.isEmpty());
	}
	
	public class InMemoryEmployeeRepository implements EmployeeRepository {

		public List<Employee> getEmployeesWhoseBirthadyIs(Date today) {
			return new ArrayList<Employee>();
		}
		
	}
}
