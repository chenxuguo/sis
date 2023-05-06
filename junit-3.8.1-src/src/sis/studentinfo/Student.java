
package sis.studentinfo;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

public class Student implements Serializable {
	public enum Grade {
		A(4),
		B(3), 
		C(2), 
		D(1), 
		F(0);
		private int points;
		Grade(int points) {
			this.points = points;
		}
		
		int getPoints() {
			return points;
		}
	}
	private List<Integer> charges = new ArrayList<Integer>();
	private String _name;
	private int credits;
	private String fromState = "";
	private ArrayList<Grade> grades = new ArrayList<>();
	public static int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
	public static String IN_STATE = "CO";
	public static enum Flag{
		ON_CAMPUS(1),
		TAX_EXEMPT(2),
		MINOR(4),
		TROUBLEMAKE(8);
		
		private int mask;
		Flag(int mask) {
			this.mask = mask;
		}	
	}
	private int settings = 0x0;

	private GradingStrategy gradingStrategy = new BasicGradingStrategy();
	private String firstName = "";
	private String middleName = "";
	private String lastName;
	final static Logger logger = 
			Logger.getLogger(Student.class.getName());

	
	public Student(String fullName){
		_name = fullName;
		credits = 0;
		List<String> nameParts = split(fullName);
		
		final int maximumNumberOfNameParts = 3;
		if (nameParts.size() > maximumNumberOfNameParts) {
			String message =
					"Student name '" + fullName +
					"' contains more than " + maximumNumberOfNameParts +
					" parts";
			Student.logger.info(message);
			Student.logger.info(message + " 2");			Student.logger.info(message);
			throw new StudentNameFormatException(message);
		}
		setName(nameParts);
	}
	private void log(String message) {
		// TODO Auto-generated method stub
		Student.logger.info(message);
	}
	public String getName() {
		return _name;
	}
	void setName(String name) {
		_name = name;
	}
	private void setName(List<String> nameParts) {
		if (nameParts.size() == 1)
			this.lastName = nameParts.get(0);
		else if (nameParts.size() == 2) {
			this.firstName = nameParts.get(0);
			this.lastName = nameParts.get(1);
		}
		else if (nameParts.size() == 3) {
			this.firstName = nameParts.get(0);
			this.middleName = nameParts.get(1);
			this.lastName = nameParts.get(2);
		}
	}
	
	private List<String> split(String name) {
		List<String> results = new ArrayList<String>();
		
		StringBuffer word = new StringBuffer();
		for (int index = 0; index < name.length(); index++) {
			char ch = name.charAt(index);
			if (!Character.isWhitespace(ch))
				word.append(ch);
			else
				if (word.length() > 0) {
					results.add(word.toString());
					word = new StringBuffer();
				}
		}
		if (word.length() > 0)
			results.add(word.toString());
		return results;
	}
	
	static List<String> tokenize(String string) {
		List<String> results = new ArrayList<String>();
		
		StringBuffer word = new StringBuffer();
		int index = 0;
		while (index < string.length()) {
			char ch = string.charAt(index);
			if (ch != ' ') // prefer Character.isSpace, Defined yet?
				word.append(ch);
			else
				if (word.length() > 0) {
					results.add(word.toString());
					word = new StringBuffer();;
				}
			index++;
		}
		if (word.length() > 0)
			results.add(word.toString());
		return results;
	}
	boolean isFullTime() {
		return credits >= Student.CREDITS_REQUIRED_FOR_FULL_TIME;
	}
	int getCredits() {
		return credits;
	}
	void addCredits(int credits) {
		this.credits += credits;
	}
	public boolean isInState() {
		// TODO Auto-generated method stub
		return (fromState.toUpperCase()).equals(Student.IN_STATE);
	}
	public void setState(String fromState) {
		this.fromState = fromState; 
	}
	public void addGrade(Grade grade) {
		this.grades.add(grade);
	}
	public double getGpa() {
		Student.logger.info("begin getGpa " + System.currentTimeMillis());
		if (grades.isEmpty())
			return 0.0;
		double total = 0;
		for (Grade grade : grades) {
			total += gradePointFor(grade);

		}
		Student.logger.info("end getGpa " + System.currentTimeMillis());
		return total / grades.size();
	}
	private double gradePointFor(Grade grade) {
		return gradingStrategy.getGradePointsFor(grade);
		
	}
//	private int basicGradePointsfor(Grade grade) {
//		switch(grade) {
//		case A:
//			return 4;
//		case B:
//			return 3;	
//		case C:
//			return 2;
//		case D:
//			return 1;
//		case F:
//			return 0;
//		default:
//			return 0;
//		}
//	}
	public void setGradingStrategy(GradingStrategy gradingStrategy) {
		// TODO Auto-generated method stub
		this.gradingStrategy = gradingStrategy;
	}
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}
	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}
	public String getMiddleName() {
		// TODO Auto-generated method stub
		return middleName;
	}
	public void addCharge(int charge) {
		// TODO Auto-generated method stub
		charges.add(new Integer(charge));
	}
	public int totalCharges() {
		int total = 0;
		for (Integer charge: charges)
			total += charge.intValue();
		return total;
	}
	public String getId() {
		// TODO Auto-generated method stub
		return _name;
	}
	public void setId(String id) {
		// TODO Auto-generated method stub
		_name = id;
	}
	public void set(Flag... flags) {
		for (Flag flag: flags)
			settings &= -flag.mask;
	}
	public void unset(Flag... flags) {
		for (Flag flag: flags)
			settings &= ~flag.mask;
	}
	public boolean isOn(Flag flag) {
		return (settings & flag.mask) == flag.mask; 
	}
	
	public boolean isOff(Flag flag) {
		return !isOn(flag);
	}
	public static Student findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return new Student(lastName);
	}
}
