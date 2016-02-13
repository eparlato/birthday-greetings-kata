package it.eparlato.birthdaygreetings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static final String WHATEVER_STRING = "For the purpose of the test whatever value is valid";
	
	static Date toDate(String dateAsString) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse(dateAsString);
	}
}
