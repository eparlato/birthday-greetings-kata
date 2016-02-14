package it.eparlato.birthdaygreetings;

import java.io.IOException;
import java.text.ParseException;
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

	private String anyString() {
		return Utils.WHATEVER_STRING;
	}
	
	private Date toDate(String dateAsString) throws ParseException {
		return Utils.toDate_dd_MM_yyyy(dateAsString);
	}
	
	@Test
	public void oneEmployeeWhoseBirthdayIsToday() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final MessageService messageService = context.mock(MessageService.class);
		
		final Employee employee = new Employee(anyString(), anyString(), toDate("03/02/1982"), anyString());
		final List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);

		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(with(toDate("03/02/2016")));
				will(returnValue(employees));

				allowing(messageService).sendGreetings(new Greetings(employee));
			}
		});

		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		birthdayService.process(toDate("03/02/2016"));
	}
	
	@Test
	public void oneEmployeeSendGreetings() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final MessageService messageService = context.mock(MessageService.class);		
		
		final Employee employee = new Employee(anyString(), anyString(), toDate("03/02/1982"), anyString());
		final List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(with(toDate("03/02/2016")));
				will(returnValue(employees));
				
				allowing(messageService).sendGreetings(new Greetings(employee));
			}
		});
		
		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		birthdayService.process(toDate("03/02/2016"));
	}

	@Test
	public void noEmployeesWhoseBirthdayIsToday() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final MessageService messageService = context.mock(MessageService.class);
		
		final Date anyDateIsOkForThisTest = toDate("03/02/2016");
		final List<Employee> noEmployees = new ArrayList<Employee>();
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(anyDateIsOkForThisTest);
				will(returnValue(noEmployees));

				never(messageService).sendGreetings(new Greetings(new Employee(anyString(), anyString(), anyDateIsOkForThisTest, anyString())));
			}
		});

		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		birthdayService.process(anyDateIsOkForThisTest);
	}

	@Test
	public void aFewEmployeesWhoseBirthdayIsToday() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final MessageService messageService = context.mock(MessageService.class);

		final Employee employeeA = new Employee(anyString(), anyString(), toDate("25/12/1906"), anyString());
		final Employee employeeB = new Employee(anyString(), anyString(), toDate("25/12/1916"), anyString());
		final Employee employeeC = new Employee(anyString(), anyString(), toDate("25/12/1956"), anyString());
		final Employee employeeD = new Employee(anyString(), anyString(), toDate("25/12/1976"), anyString());
		final Employee employeeE = new Employee(anyString(), anyString(), toDate("25/12/1996"), anyString());
		final List<Employee> employees = new ArrayList<Employee>();

		employees.add(employeeA);
		employees.add(employeeB);
		employees.add(employeeC);
		employees.add(employeeD);
		employees.add(employeeE);

		final Date today = toDate("25/12/2016");

		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(with(today));
				will(returnValue(employees));

				allowing(messageService).sendGreetings(new Greetings(employeeA));
				allowing(messageService).sendGreetings(new Greetings(employeeB));
				allowing(messageService).sendGreetings(new Greetings(employeeC));
				allowing(messageService).sendGreetings(new Greetings(employeeD));
				allowing(messageService).sendGreetings(new Greetings(employeeE));
			}
		});

		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		birthdayService.process(today);
	}

	@Test
	public void errorWhileRetrievingEmployeesWhoseBirthdayIsToday() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final MessageService messageService = context.mock(MessageService.class);
		final Date anyDateIsOkForThisTest = toDate("15/08/2000");
		
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(with(anyDateIsOkForThisTest));
				will(throwException(new IOException()));
				
				never(messageService).sendGreetings(new Greetings(new Employee(anyString(), anyString(), anyDateIsOkForThisTest, anyString())));
			}
		});
		
		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		birthdayService.process(anyDateIsOkForThisTest);
	}

}
