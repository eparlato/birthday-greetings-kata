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
		return Utils.toDate(dateAsString);
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

				oneOf(messageService).sendGreetingsToEmployee(employee);
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

				never(messageService).sendGreetingsToEmployee(new Employee(anyString(), anyString(), anyDateIsOkForThisTest, anyString()));
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

				oneOf(messageService).sendGreetingsToEmployee(employeeA);
				oneOf(messageService).sendGreetingsToEmployee(employeeB);
				oneOf(messageService).sendGreetingsToEmployee(employeeC);
				oneOf(messageService).sendGreetingsToEmployee(employeeD);
				oneOf(messageService).sendGreetingsToEmployee(employeeE);
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
				
				never(messageService).sendGreetingsToEmployee(new Employee(anyString(), anyString(), anyDateIsOkForThisTest, anyString()));
			}
		});
		
		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		birthdayService.process(anyDateIsOkForThisTest);
	}
	
	public class BirthdayService {

		private EmployeeRepository employeeRepository;
		private MessageService messageService;

		public BirthdayService(EmployeeRepository employeeRepository, MessageService messageService) {
			this.employeeRepository = employeeRepository;
			this.messageService = messageService;
		}

		public void process(Date today) {

			List<Employee> employeesWhoseBirthdayIsToday;
			try {
				employeesWhoseBirthdayIsToday = employeeRepository.getEmployeesWhoseBirthadyIs(today);

				for (Employee employee : employeesWhoseBirthdayIsToday) {
					messageService.sendGreetingsToEmployee(employee);
				}

			} catch (IOException e) {
				// TODO 1: Move this catch block into EmployeeRepository. Errors and exceptions will be handled 
				// by EmployeeRepository. BirthdayService doesn't check data integrity, it just send greetings messages.
				// TODO 2: create a new ErrorManager interface?
				System.err.println("Error to deal with");
			}

		}
	}

	public interface MessageService {
		void sendGreetingsToEmployee(Employee employee);
	}

}
