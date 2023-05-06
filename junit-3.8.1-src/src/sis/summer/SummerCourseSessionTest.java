package sis.summer;

import java.util.*;
import sis.studentinfo.*;

public class SummerCourseSessionTest extends SessionTest{
	public void testEndDate() {
		Date startDate = DateUtil.createDate(2003,  6, 9);
		Session session = createSession(new Course("ENGL", "200"), startDate); 
			
		Date eightWeeksOut = DateUtil.createDate(2003, 8,1);
		assertEquals(eightWeeksOut, session.getEndDate());
	}
	
	protected Session createSession(
			String department,
			String number,
			Date date) {
		return SummerCourseSession.create(new Course(department,  number),  date);
	}

	@Override
	protected Session createSession(Course course, Date startDate) {
		// TODO Auto-generated method stub
		return SummerCourseSession.create(course, startDate);
	}
}
