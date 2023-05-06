package sis.studentinfo;
import java.util.*;

public class CourseSessionTest extends SessionTest {
	
	public void testCourseDates() {
		Date startDate = DateUtil.createDate(2003,  1,  6);
		Session session = createSession(new Course("ENGL", "200"), startDate);
		java.util.Date sixteenWeeksOut = DateUtil.createDate(2003, 4, 25);
		assertEquals(sixteenWeeksOut, session.getEndDate());
	}
	public void testCount() {
		CourseSession.resetCount();
		createSession(new Course("", ""), new Date());
		assertEquals(1, CourseSession.getCount());
		createSession(new Course("", ""), new Date());
		assertEquals(2, CourseSession.getCount());
	}
	protected Session createSession(
			String department,
			String number,
			Date date) {
		return CourseSession.create(new Course(department, number), date);
	}
	@Override
	protected Session createSession(Course course, Date startDate) {
		// TODO Auto-generated method stub
		return CourseSession.create(course, startDate);
	}
}
