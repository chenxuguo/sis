package sis.studentinfo;

public class StudentNameFormatException extends IllegalArgumentException {
	public StudentNameFormatException(String message) {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return "Student name 'a b c d' contains more than 3 parts"; 
	}
}
