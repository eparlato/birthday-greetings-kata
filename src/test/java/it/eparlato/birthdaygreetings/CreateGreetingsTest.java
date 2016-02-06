package it.eparlato.birthdaygreetings;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CreateGreetingsTest {

	@Rule
	public final JUnitRuleMockery context = new JUnitRuleMockery();
	
	@Test
	public void oneEmployeeWhoseBirthdayIsToday() throws Exception {
		Date employeeDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1982");
		
		final Employee employee = new Employee(employeeDateOfBirth);
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);	
		final MessageService messageService = context.mock(MessageService.class);
		final List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		
		final Date today = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2016");
		
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(with(today));
				will(returnValue(employees));
				
				oneOf(messageService).sendGreetingsToEmployee(employee);
			}
		});
		
		GreetingsController greetingsController  = new GreetingsController(employeeRepository, messageService);	
		greetingsController.process(today);
	}
	
	@Test
	public void noEmployeesWhoseBirthdayIsToday() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final MessageService messageService = context.mock(MessageService.class);
		final Date uselessDate = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2016");
		final List<Employee> noEmployees = new ArrayList<Employee>();
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(uselessDate);
				will(returnValue(noEmployees));
				
				never(messageService).sendGreetingsToEmployee(new Employee(uselessDate));
			}
		});
		
		GreetingsController greetingsController = new GreetingsController(employeeRepository, messageService);
		greetingsController.process(uselessDate);
	}
	
	@Test
	public void aFewEmployeesWhoseBirthdayIsToday() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);	
		final MessageService messageService = context.mock(MessageService.class);
		
		final Employee employeeA = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1906"));
		final Employee employeeB = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1916"));
		final Employee employeeC = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1956"));
		final Employee employeeD = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1976"));
		final Employee employeeE = new Employee(new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1996"));
		final List<Employee> employees = new ArrayList<Employee>();
		
		employees.add(employeeA);
		employees.add(employeeB);
		employees.add(employeeC);
		employees.add(employeeD);
		employees.add(employeeE);
		
		final Date today = new SimpleDateFormat("dd/MM/yyyy").parse("25/12/2016");
		
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(with(today));
				will(returnValue(employees));
				
				oneOf(messageService).sendGreetingsToEmployee(employeeA);
				oneOf(messageService).sendGreetingsToEmployee(employeeB);
				oneOf(messageService).sendGreetingsToEmployee(employeeC);
				oneOf(messageService).sendGreetingsToEmployee(employeeD);
				oneOf(messageService).sendGreetingsToEmployee(employeeE);
			}
		});
		
		GreetingsController greetingsController  = new GreetingsController(employeeRepository, messageService);	
		greetingsController.process(today);
	}
	
	public class GreetingsController {

		private EmployeeRepository employeeRepository;
		private MessageService messageService;
		
		public GreetingsController(EmployeeRepository employeeRepository, MessageService messageService) {
			this.employeeRepository = employeeRepository;
			this.messageService = messageService;
		}

		public void process(Date today) {
			
			List<Employee> employeesWhoseBirthdayIsToday = employeeRepository.getEmployeesWhoseBirthadyIs(today);
			
			for(Employee employee : employeesWhoseBirthdayIsToday) {
				messageService.sendGreetingsToEmployee(employee);
			}
		}
	}

	
	public interface EmployeeRepository {
		public List<Employee> getEmployeesWhoseBirthadyIs(Date today);
	}
	
	public interface MessageService {
		void sendGreetingsToEmployee(Employee employee);
	}

}
