package it.eparlato.birthdaygreetings;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CreateGreetingsTest {

	private final String WHATEVER = "For the purpose of the test whatever value is valid";

	@Rule
	public final JUnitRuleMockery context = new JUnitRuleMockery();

	@Test
	public void oneEmployeeWhoseBirthdayIsToday() throws Exception {
		Date employeeDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/1982");

		final Employee employee = new Employee(WHATEVER, WHATEVER, employeeDateOfBirth, WHATEVER);
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

		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		birthdayService.process(today);
	}

	@Test
	public void noEmployeesWhoseBirthdayIsToday() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final MessageService messageService = context.mock(MessageService.class);
		final Date anyDateIsOkForThisTest = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2016");
		final List<Employee> noEmployees = new ArrayList<Employee>();
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(anyDateIsOkForThisTest);
				will(returnValue(noEmployees));

				never(messageService).sendGreetingsToEmployee(new Employee(WHATEVER, WHATEVER, anyDateIsOkForThisTest, WHATEVER));
			}
		});

		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		birthdayService.process(anyDateIsOkForThisTest);
	}

	@Test
	public void aFewEmployeesWhoseBirthdayIsToday() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final MessageService messageService = context.mock(MessageService.class);

		final Employee employeeA = new Employee(WHATEVER, WHATEVER, new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1906"), WHATEVER);
		final Employee employeeB = new Employee(WHATEVER, WHATEVER, new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1916"), WHATEVER);
		final Employee employeeC = new Employee(WHATEVER, WHATEVER, new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1956"), WHATEVER);
		final Employee employeeD = new Employee(WHATEVER, WHATEVER, new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1976"), WHATEVER);
		final Employee employeeE = new Employee(WHATEVER, WHATEVER, new SimpleDateFormat("dd/MM/yyyy").parse("25/12/1996"), WHATEVER);
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

		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		birthdayService.process(today);
	}

	@Test
	public void errorWhileRetrievingEmployeesWhoseBirthdayIsToday() throws Exception {
		final EmployeeRepository employeeRepository = context.mock(EmployeeRepository.class);
		final MessageService messageService = context.mock(MessageService.class);
		final Date anyDateIsOkForThisTest = new SimpleDateFormat("dd/MM/yyyy").parse("15/08/2000");
		
		context.checking(new Expectations() {
			{
				allowing(employeeRepository).getEmployeesWhoseBirthadyIs(with(anyDateIsOkForThisTest));
				will(throwException(new IOException()));
				
				never(messageService).sendGreetingsToEmployee(new Employee(WHATEVER, WHATEVER, anyDateIsOkForThisTest, WHATEVER));
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
