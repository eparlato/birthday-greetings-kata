package it.eparlato.birthdaygreetings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

public class CreateGreetingsTest {

	@Rule
	public final JUnitRuleMockery context = new JUnitRuleMockery();
	
	@Test
	public void oneEmployeeCreateGreetings() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		
		final List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee());
		
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployees();
				will(returnValue(employees));
			}
		});
		
		GreetingsController greetingsController  = new GreetingsController(employeeRepository);	
		greetingsController.process();
		
		assertEquals(1, greetingsController.getGreetings().size());
	}
	
	public class GreetingsController {

		private EmployeeRepository employeeRepository;
		private List<Greetings> greetingsList = new ArrayList<Greetings>();
		
		public GreetingsController(EmployeeRepository employeeRepository) {
			this.employeeRepository = employeeRepository;
		}

		public List<Greetings> getGreetings() {
			return greetingsList;
		}

		public void process() {
			Employee employee = employeeRepository.getEmployees().get(0);
			greetingsList.add(new Greetings(employee));
		}
	}

	
	public interface EmployeeRepository {

		public List<Employee> getEmployees();

	}
	 
	private class Employee {

	}
	
	private class Greetings {

		public Greetings(Employee employee) {
		}

	}

}
