package it.eparlato.birthdaygreetings;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		Date employeeDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1982");
		
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);		
		final List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(employeeDateOfBirth));
		
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
	
	@Test
	public void oneEmployeeDoNotCreateGreetings() throws Exception {
		Date employeeDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("02/03/1982");

		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(employeeDateOfBirth));
		
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployees();
				will(returnValue(employees));
			}
		});
		
		GreetingsController greetingsController  = new GreetingsController(employeeRepository);	
		greetingsController.process();
		
		assertEquals(0, greetingsController.getGreetings().size());
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

		public void process() throws ParseException {
			Employee employee = employeeRepository.getEmployees().get(0);
			
			Date today = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2016");
			
			if(employee.isBirthday(today)) {
				greetingsList.add(new Greetings(employee));
			}
		}
	}

	
	public interface EmployeeRepository {

		public List<Employee> getEmployees();

	}
	 
	private class Greetings {

		public Greetings(Employee employee) {
		}

	}

}
