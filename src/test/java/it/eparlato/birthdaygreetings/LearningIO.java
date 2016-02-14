package it.eparlato.birthdaygreetings;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LearningIO {

	public static void main(String[] args) throws IOException {
		String fakeInputFile = 
				"last_name, first_name, date_of_birth, email\n"+ 
				"Doe, John, 1982/10/08, john.doe@foobar.com\n" +
				"Ann, Mary, 1975/09/11, mary.ann@foobar.com\n" +
				"Ford, Ed, 1982/11/24, ed.ford@foobar.com";
		
		LearningIO learning = new LearningIO();
		
		InputStream is = new ByteArrayInputStream(fakeInputFile.getBytes());
		learning.showContentOfMyStream(is);
		
		is = new FileInputStream("employee_data.txt");
		learning.showContentOfMyStream(is);
	}
	
	
	private void showContentOfMyStream(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = 
				new BufferedReader(new InputStreamReader(inputStream));
		
		String line;
		
		System.out.println("> Beginning of input stream...");
		
		while((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println("> ...End");
		
		bufferedReader.close();
	}

}
