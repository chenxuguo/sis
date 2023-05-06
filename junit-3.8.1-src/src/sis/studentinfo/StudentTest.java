package sis.studentinfo;

import java.util.*;
import java.util.logging.*;

public class StudentTest extends junit.framework.TestCase {
	static final double GRADE_TOLERANCE = 1.0e-6;
	public void testCreate() {
		final String firstStudentName = "Jane Doe";
		Student firstStudent = new Student(firstStudentName);
		//firstStudent.setName("Juns Crow");
		assertEquals(firstStudentName, firstStudent.getName());
		assertEquals("Jane", firstStudent.getFirstName());
		assertEquals("Doe", firstStudent.getLastName());
		assertEquals("", firstStudent.getMiddleName());
		final String secondStudentName = "Blow";
		Student secondStudent = new Student(secondStudentName);
		assertEquals(secondStudentName, secondStudent.getName());
		assertEquals("", secondStudent.getFirstName());
		assertEquals("Blow", secondStudent.getLastName());
		assertEquals("", secondStudent.getMiddleName());
		
		final String thirdStudentName = "Raymond Douglas Davies";
		Student thirdStudent = new Student(thirdStudentName);
		assertEquals(thirdStudentName, thirdStudent.getName());
		assertEquals("Raymond", thirdStudent.getFirstName());
		assertEquals("Davies", thirdStudent.getLastName());
		assertEquals("Douglas", thirdStudent.getMiddleName());
		assertEquals(firstStudentName, firstStudent.getName());
	}
	public void testFullTime() {
		Student student = new Student("a");
		assertFalse(student.isFullTime());
	}
	public void testCredits() {
		Student student = new Student("a");
		assertEquals(0, student.getCredits());
		student.addCredits(3);
		assertEquals(3, student.getCredits());
		student.addCredits(4);
		assertEquals(7, student.getCredits());
		
	}
	public void testStudentStatus() {
		Student student = new Student("a");
		assertEquals(0, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(3);
		assertEquals(3, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(4);
		assertEquals(7, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(5);
		assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
		assertTrue(student.isFullTime());
		
	}
	public void testInState() {
		Student student = new Student("e");
		assertFalse(student.isInState());
		student.setState(Student.IN_STATE);
		assertTrue(student.isInState());
		student.setState("MD");
		assertFalse(student.isInState());
	}
	public void testCalculateGpa() {
		Student student = new Student("a");
		assertGpa(student, 0.0);
		student.addGrade(Student.Grade.A);
		assertGpa(student, 4.0);
		student.addGrade(Student.Grade.B);
		assertGpa(student, 3.5);
		student.addGrade(Student.Grade.C);
		assertGpa(student, 3.0);
		student.addGrade(Student.Grade.D);
		assertGpa(student, 2.5);
		student.addGrade(Student.Grade.F);
		assertGpa(student, 2.0);
	}
	private void assertGpa(Student student, double expectedGpa  ) {
		assertEquals(expectedGpa, student.getGpa(), StudentTest.GRADE_TOLERANCE);
	}
	public void testCalculateHonorsStudentGpa() {
		assertGpa(createHonorsStudent(), 0.0);
		assertGpa(createHonorsStudent(Student.Grade.A), 5.0);
		assertGpa(createHonorsStudent(Student.Grade.B), 4.0);
		assertGpa(createHonorsStudent(Student.Grade.C), 3.0);
		assertGpa(createHonorsStudent(Student.Grade.D), 2.0);
		assertGpa(createHonorsStudent(Student.Grade.F), 0.0);
	}
	
	private Student createHonorsStudent(Student.Grade grade) {
		Student student = createHonorsStudent();
		student.addGrade(grade);
		return student;
	}
	private Student createHonorsStudent() {
		Student student = new Student("A");
		student.setGradingStrategy(new HonorsGradingStrategy());
		return student;
	}
	public void testCasting() {
		List students = new ArrayList();
		students.add(new Student("a"));
		students.add(new Student("b"));
		
		List names = new ArrayList();
		
		Iterator it = students.iterator();
		while (it.hasNext()) {
			Student student = (Student)it.next();
			names.add(student.getLastName());
		}
		
		assertEquals("a", names.get(0));
		assertEquals("b", names.get(1));
	}
	
	public void testCharges() {
		Student student = new Student("a");
		student.addCharge(500);
		student.addCharge(200);
		student.addCharge(399);
		assertEquals(1099, student.totalCharges());
	}
	public void testBadlyFormattername() {
		Logger logger = Logger.getLogger(Student.class.getName());
		Handler handler = new TestHandler();
		logger.addHandler(handler);
		try {
			new Student("a b c d");
			fail("expected exception from 4-part name");
		} 
		catch (StudentNameFormatException success) {
			//System.out.println(success.getMessage());
			String message = "Student name 'a b c d' contains more than 3 parts";
			assertEquals("Student name 'a b c d' contains more than 3 parts", success.getMessage());
			assertTrue(wasLogged(message, (TestHandler)handler));
		}
	}
	private boolean wasLogged(String message, TestHandler handler) {
		// TODO Auto-generated method stub
		return message.equals(handler.getMessage());
	}
	public void testFlags() {
		Student student = new Student("a");
		student.set(
				Student.Flag.ON_CAMPUS,
				Student.Flag.TAX_EXEMPT,
				Student.Flag.MINOR);
		assertTrue(student.isOn(Student.Flag.ON_CAMPUS));
		assertTrue(student.isOff(Student.Flag.TROUBLEMAKE));
		
		student.unset(Student.Flag.ON_CAMPUS);
		assertTrue(student.isOff(Student.Flag.ON_CAMPUS));
		assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
		assertTrue(student.isOn(Student.Flag.MINOR));	
	}
}
