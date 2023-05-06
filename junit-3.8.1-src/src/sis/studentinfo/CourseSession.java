package sis.studentinfo;
import java.util.*;
/** 
 * Provides a representation of a single-semester 
 * session of a specific university course
 * @author Administrator
 */
public class CourseSession extends Session {
	private static int count ;
	public static CourseSession create(
			String department,
			String number,
			Date startDate) {
		return new CourseSession(department, number, startDate);
	}
	protected CourseSession(String department,  String number, java.util.Date startDate) {
		super(department, number, startDate);
		CourseSession.incrementCount();
	}
	public CourseSession(Course course, Date date) {
		// TODO Auto-generated constructor stub
		super(course, date);
		CourseSession.incrementCount();
	}
	protected int getSessionLength() {
		return 16;
	}
	static int getCount() {
		return CourseSession.count;
	}
	static void resetCount() {
		CourseSession.count = 0;
	}
	static void incrementCount() {
		CourseSession.count++;
	}
	public static Session create(Course course, Date date) {
		// TODO Auto-generated method stub
		return new CourseSession(course, date);
	}
}
