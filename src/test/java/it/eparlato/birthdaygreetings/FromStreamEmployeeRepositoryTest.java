package it.eparlato.birthdaygreetings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class FromStreamEmployeeRepositoryTest {

	String fakeInputStream = 
			"last_name, first_name, date_of_birth, email\n" +
			"Doe, John, 1982/10/08, john.doe@foobar.com\n" +
			"Ann, Mary, 1975/09/11, mary.ann@foobar.com\n" +
			"Ford, Frank, 1981/09/11, frank.ford@foobar.com\n" +
			"Smith, Winston, 1982/10/09, winston.smith@foobar.com\n" +
			"Wesson, Alan, 1971/09/11, alan.wesson@foobar.com"; 

	@Test
	public void noEmployeesWhoseBirthdayIsToday() throws Exception {
		
		EmployeeRepository fromStreamEmployeeRepository = new FromStreamEmployeeRepository(new ByteArrayInputStream(fakeInputStream.getBytes()));
		
		List<Employee> employees = 
				fromStreamEmployeeRepository.getEmployeesWhoseBirthadyIs(Utils.toDate_dd_MM_yyyy("15/08/2015"));
		
		assertTrue(employees.isEmpty());
	}
	
	@Test
	@Ignore("I need a new Utils.toDate() method to deal with date in format yyyy/MM/dd")
	public void oneEmployeeWhoseBirthdayIsToday() throws Exception {
		EmployeeRepository fromStreamEmployeeRepository = new FromStreamEmployeeRepository(new ByteArrayInputStream(fakeInputStream.getBytes()));

		Employee expectedEmployee = new Employee("Doe", "John", Utils.toDate_dd_MM_yyyy("08/10/1982"), "john.doe@foobar.com");
		
		List<Employee> employees = 
				fromStreamEmployeeRepository.getEmployeesWhoseBirthadyIs(Utils.toDate_dd_MM_yyyy("08/10/2015"));
		
		assertEquals(expectedEmployee, employees.get(0));
	}
	
	public class FromStreamEmployeeRepository implements EmployeeRepository {

		private InputStream byteArrayInputStream;

		public FromStreamEmployeeRepository(InputStream byteArrayInputStream) {
			this.byteArrayInputStream = byteArrayInputStream;
		}

		public List<Employee> getEmployeesWhoseBirthadyIs(Date today) throws IOException {
			return new ArrayList<Employee>();
		}

	}
}
