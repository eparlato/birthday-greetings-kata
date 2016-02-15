package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class FromStreamEmployeeRepositoryTest {

	String fakeInputStream = "last_name, first_name, date_of_birth, email\n"
			+ "Doe, John, 1982/10/08, john.doe@foobar.com\n" + "Ann, Mary, 1975/09/11, mary.ann@foobar.com\n"
			+ "Ford, Frank, 1981/09/11, frank.ford@foobar.com\n"
			+ "Smith, Winston, 1982/10/09, winston.smith@foobar.com\n"
			+ "Wesson, Alan, 1971/09/11, alan.wesson@foobar.com";

	@Test
	public void noEmployeesWhoseBirthdayIsToday() throws Exception {

		EmployeeRepository fromStreamEmployeeRepository = new FromStreamEmployeeRepository(new ByteArrayInputStream(
				fakeInputStream.getBytes()));

		List<Employee> employees = fromStreamEmployeeRepository.getEmployeesWhoseBirthadyIs(Utils
				.toDate_yyyy_MM_dd("2015/08/15"));

		assertTrue(employees.isEmpty());
	}

	@Test
	public void oneEmployeeWhoseBirthdayIsToday() throws Exception {
		EmployeeRepository fromStreamEmployeeRepository = new FromStreamEmployeeRepository(new ByteArrayInputStream(
				fakeInputStream.getBytes()));

		Employee expectedEmployee = new Employee("Doe", "John", Utils.toDate_yyyy_MM_dd("1982/10/08"),
				"john.doe@foobar.com");

		List<Employee> employees = fromStreamEmployeeRepository.getEmployeesWhoseBirthadyIs(Utils
				.toDate_yyyy_MM_dd("1982/10/08"));

		assertEquals(expectedEmployee, employees.get(0));
	}

	@Test
	public void aFewEmployeesWhoseBirthdayIsToday() throws Exception {
		EmployeeRepository fromStreamEmployeeRepository = new FromStreamEmployeeRepository(new ByteArrayInputStream(
				fakeInputStream.getBytes()));

		List<Employee> employees = fromStreamEmployeeRepository.getEmployeesWhoseBirthadyIs(Utils
				.toDate_yyyy_MM_dd("2015/09/11"));

		assertTrue(employees.contains(new Employee("Ann", "Mary", Utils.toDate_yyyy_MM_dd("1975/09/11"),
				"mary.ann@foobar.com")));
		assertTrue(employees.contains(new Employee("Ford", "Frank", Utils.toDate_yyyy_MM_dd("1981/09/11"),
				"frank.ford@foobar.com")));
		assertTrue(employees.contains(new Employee("Wesson", "Alan", Utils.toDate_yyyy_MM_dd("1971/09/11"),
				"alan.wesson@foobar.com")));
		assertEquals(3, employees.size());
	}

	public class FromStreamEmployeeRepository implements EmployeeRepository {

		private InputStream inputStream;

		public FromStreamEmployeeRepository(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public List<Employee> getEmployeesWhoseBirthadyIs(Date today) {
			List<Employee> employees = new ArrayList<Employee>();

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			try {
				String line = reader.readLine();
				while ((line = reader.readLine()) != null) {
					String[] rowData = line.split(",");

					try {
						Employee employee = new Employee(rowData[0], rowData[1].trim(),
								Utils.toDate_yyyy_MM_dd(rowData[2]), rowData[3].trim());
						if (employee.isBirthday(today)) {
							employees.add(employee);
						}
					} catch (ParseException ex) {
						logError(ex);
					}
				}
				
				reader.close();
				
			} catch (IOException ex) {
				logError(ex);
			}

			return employees;
		}

		private void logError(Exception ex) {
			System.err.println("Error: " + ex.toString());
		}

	}
}
