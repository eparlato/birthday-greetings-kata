package it.eparlato.birthdaygreetings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		employeesWhoseBirthdayIsToday = employeeRepository.getEmployeesWhoseBirthadyIs(today);

		for (Employee employee : employeesWhoseBirthdayIsToday) {
			messageService.sendGreetings(new Greetings(employee));
		}
	}

	public static void main(String[] args) throws ParseException, FileNotFoundException {
		EmployeeRepository fromStreamEmployeeRepository = 
				new FromStreamEmployeeRepository(new FileInputStream("employee_data.txt"));
		MessageService smtpMessageService = new SMTPMessageService("localhost", 25, "local@host.com");
		
		BirthdayService birthdayService = new BirthdayService(fromStreamEmployeeRepository, smtpMessageService);
		Date todayIs = Utils.toDate_yyyy_MM_dd("2016/10/08");
		
		birthdayService.process(todayIs);
	}


}