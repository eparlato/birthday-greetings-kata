package it.eparlato.birthdaygreetings;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
				messageService.sendGreetings(new Greetings(employee));
			}

		} catch (IOException e) {
			// TODO 1: Move this catch block into EmployeeRepository. Errors and exceptions will be handled 
			// by EmployeeRepository. BirthdayService doesn't check data integrity, it just send greetings messages.
			// TODO 2: create a new ErrorManager interface?
			System.err.println("Error to deal with");
		}
	}
}