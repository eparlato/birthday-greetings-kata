package it.eparlato.birthdaygreetings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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