package sis.summer;
import java.util.*;
import sis.studentinfo.*;

public class SummerCourseSession extends Session {
	public static Session create(
			String department,
			String number,
			Date startDate) {
		return new SummerCourseSession(department, number, startDate);
	}
	private SummerCourseSession(
			String department,
			String number,
			Date startDate) {
		super(department, number, startDate);
	}
	
	public SummerCourseSession(Course course, Date date) {
		// TODO Auto-generated constructor stub
		super(course, date);
	}
	@Override
	protected int getSessionLength() {
		return 8;
	}
	public static Session create(Course course, Date date) {
		// TODO Auto-generated method stub
		return new SummerCourseSession(course, date);
	}
}
