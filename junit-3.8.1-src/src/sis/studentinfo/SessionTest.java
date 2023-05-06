package sis.studentinfo;

import junit.framework.*;
import java.util.*;
import static sis.studentinfo.DateUtil.createDate;
import java.net.*;

public abstract class SessionTest extends TestCase{
	private Session session;
	private Date startDate;
	public static final int CREDITS = 3;
	
	public void setUp() {
		startDate = createDate(2003, 1, 6);
		session = createSession(new Course("ENGL", "101"), startDate);
		session.setNumberOfCredits(CREDITS);
	}
	
	abstract protected Session createSession(
			Course course, Date startDate);
	
	public void testCreate() {
		assertEquals("ENGL", session.getDepartment());
		assertEquals("101", session.getNumber());
		assertEquals(0, session.getNumberOfStudents());
		assertEquals(startDate, session.getStartDate());
		
	}
	public void testEnrollStudents() {
		Student student1 = new Student("Cain Divoe");
		session.enroll(student1);
		assertEquals(CREDITS, student1.getCredits());
		assertEquals(1, session.getNumberOfStudents());
		assertEquals(student1, session.get(0));
		
		Student student2 = new Student("Coralee DeVaughn");
		session.enroll(student2);
		assertEquals(CREDITS, student2.getCredits());
		assertEquals(2, session.getNumberOfStudents());
		assertEquals(student1, session.get(0));
		assertEquals(student2, session.get(1));
	}
	
	public void testComparable() {
		final Date date = new Date();
		Session sessionA = createSession(new Course("CMSC", "101"), date);
		Session sessionB = createSession(new Course("ENGL", "101"), date);
		assertTrue(sessionA.compareTo(sessionB) < 0);
		assertTrue(sessionB.compareTo(sessionA) > 0);
	
		Session sessionC = createSession(new Course("CMSC", "101"), date);
		assertEquals(0, sessionA.compareTo(sessionC));
	
		Session sessionD = createSession(new Course("CMSC", "210"), date);
		assertTrue(sessionC.compareTo(sessionD) < 0);
		assertTrue(sessionD.compareTo(sessionC) > 0);
	
	}
	
	public void testIterate() {
		enrollStudents(session);
		
		List<Student> results = new ArrayList<Student>();
		for (Student student : session)
			results.add(student);
		
		assertEquals(session.getAllStudents(), results);
	}
	
	private void enrollStudents(Session session) {
		session.enroll(new Student("1"));
		session.enroll(new Student("2"));
		session.enroll(new Student("3"));
	}
	public void testSessionUrl() throws MalformedURLException, SessionException{
		final String url = "http://course.langrsoft.com/cmsc300";
		session.setUrl(url);
		assertEquals(url, session.getUrl().toString());
	}
	public void testInvalidSessionUrl() {
		final String url = "httsp://course.langsoft.com/cmsc300";
		try {
			session.setUrl(url);
			fail("expected exception due to invalid protocol in URL");
		}
		catch (SessionException expectedException) {
			// TODO Auto-generated catch block
			Throwable cause = expectedException.getCause();
			assertEquals(MalformedURLException.class, cause.getClass());
		}
	}
	
}
