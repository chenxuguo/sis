package sis.studentinfo;

import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.*;
import java.net.*;


abstract public class Session implements Comparable<Session>, Iterable<Student>, java.io.Serializable {
	private String department;
	private String number;
	private Course course;
	private Date startDate;
	private transient List<Student> students = new ArrayList<Student>();
	private int numberOfCredits;
	private URL url;
	
	protected Session(
			String department,
			String number,
			Date startDate) {
		this.department = department;
		this.number = number;
		this.startDate = startDate;
	}
	public Session(Course course, Date startDate) {
		// TODO Auto-generated constructor stub
		this.course = course;
		this.startDate = startDate;
	}
	public static Session create(
			String department, 
			String number, 
			Date startDate) {
		return null;
	}
	public static Session create(
			Course department, 
			Date startDate) {
		return null;
	}

	void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
	public String getDepartment() {
		return course.getDepartment();
	}

	public String getNumber() {
		return course.getNumber();
	}

	public int getNumberOfStudents() {
		return students.size();
	}

	public void enroll(Student student) {
		student.addCredits(numberOfCredits);
		students.add(student);
	}

	public Student get(int index) {
		// TODO Auto-generated method stub
		return students.get(index);
	}

	public List<Student> getAllStudents() {
		return students;
	}
	protected java.util.Date getStartDate() {
		return startDate;
	}

	abstract protected int getSessionLength();
	
	public Date getEndDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getStartDate());
		int daysInWeek = 7;
		int daysFromFridayToMonday = 3;
		int mumberOfDays =
				getSessionLength() * daysInWeek - daysFromFridayToMonday;
		calendar.add(Calendar.DAY_OF_YEAR, mumberOfDays);
		return calendar.getTime();
		
	}

	public int compareTo(Session that) {
		int compare = this.getDepartment().compareTo(that.getDepartment());
		if (compare == 0)
			compare = this.getNumber().compareTo(that.getNumber());
		return compare;
	}
	public Iterator<Student> iterator() {
		return students.iterator();
	}
	public void setUrl(String urlString) throws SessionException {
		// TODO Auto-generated method stub
		try {
			this.url = new URL(urlString); 
		}
		catch (MalformedURLException e) {
			log(e);
			throw new SessionException(e);
		}
		
	}
	private void log(Exception e) {
		e.printStackTrace();
	}
	public URL getUrl() {
		return url;
	}
	public int getNumberOfCredits() {
		return  numberOfCredits;
	}
	
	private void writeObject(ObjectOutputStream output)
			throws IOException {
		output.defaultWriteObject();
		output.writeInt(students.size());
		for (Student student: students)
			output.writeObject(student.getLastName());
	}
	
	private void readObject(ObjectInputStream input)
			throws Exception {
		input.defaultReadObject();
		students = new ArrayList<Student>();
		int size = input.readInt();
		for (int i = 0; i < size; i++) {
			String lastName = (String)input.readObject();
			students.add(Student.findByLastName(lastName));
		}
	}
}