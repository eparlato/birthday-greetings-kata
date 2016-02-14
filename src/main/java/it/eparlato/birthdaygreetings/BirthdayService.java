package it.eparlato.birthdaygreetings;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
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
	
	public static void main(String[] args) throws ParseException {
		EmployeeRepository employeeRepository = new InMemoryEmployeeRepository(
				Arrays.asList(
						new Employee("Ferrari", "Carlo", Utils.toDate_dd_MM_yyyy("25/12/1956"), "carlo.ferrari@megaditta.it"),
						new Employee("Brambilla", "Piero", Utils.toDate_dd_MM_yyyy("24/12/1978"), "piero.brambilla@megaditta.it"),
						new Employee("Scarpa", "Franco", Utils.toDate_dd_MM_yyyy("25/12/1981"), "franco.scarpa@megaditta.it"),
						new Employee("Esposito", "Gennaro", Utils.toDate_dd_MM_yyyy("25/11/1993"), "gennaro.esposito@megaditta.it")
						)
				);
		MessageService messageService = new ConsoleMessageService();
		
		BirthdayService birthdayService = new BirthdayService(employeeRepository, messageService);
		
		String todayIs = "25/12/2016";
		birthdayService.process(Utils.toDate_dd_MM_yyyy(todayIs));
	}
}