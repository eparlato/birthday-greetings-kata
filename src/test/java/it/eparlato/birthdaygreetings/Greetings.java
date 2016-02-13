package it.eparlato.birthdaygreetings;

public class Greetings {

	private Employee employee;

	public Greetings(Employee employee) {
		this.employee = employee;
	}

	public String getSubject() {
		return "Happy Birthday!";
	}

	public String getDestination() {
		return employee.getEmail();
	}

	public String getMessage() {
		return String.format("Happy birthday, dear %s!", employee.getFirstName());
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Greetings) {
			Greetings that = (Greetings)obj;
			
			if(this.getSubject().equals(that.getSubject())
				&& (this.getMessage().equals(that.getMessage()))
				&& (this.getDestination().equals(that.getDestination()))
					) {
				return true;
			}
		}
		
		return false;
	}

}