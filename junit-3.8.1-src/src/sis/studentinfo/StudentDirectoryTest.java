package sis.studentinfo;
import java.io.IOException;

import junit.framework.*;
import java.io.*;

public class StudentDirectoryTest extends TestCase {
	private StudentDirectory dir;
	
	protected void setUp() throws IOException {
		dir = new StudentDirectory();
	}
	
	protected void tearDown() throws IOException {
		dir.close();
		dir.remove();
	}
	public void testScoreAndRetrieve() throws IOException {
		final int numberOfStudents = 10;
		
		for (int i = 0; i < numberOfStudents; i++)
			addStudent(dir, i);
		dir.close();
		
		dir = new StudentDirectory();
		for (int i = 0; i < numberOfStudents; i++)
			verifyStudentLookup(dir, i);
		
	}
	
	void addStudent(StudentDirectory directory, int i)
			throws IOException {
		String id = "" + i;
		Student student = new Student(id);
		student.setId(id);
		student.addCredits(i);
		directory.add(student);
	}
	
	private void verifyStudentLookup(StudentDirectory dir2, int i) 
	throws IOException {
		// TODO Auto-generated method stub
		String id = "" + i;
		Student student = dir.findById(id);
		assertEquals(id, student.getLastName());
		assertEquals(id, student.getId());
		assertEquals(i, student.getCredits());
	}

}
