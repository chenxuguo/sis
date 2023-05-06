package sis.studentinfo;

import java.io.IOException;
import java.util.*;
import sis.db.*;

public class StudentDirectory {
	private static final String DIR_BASE_NAME = "studentDir";
	private DataFile db;
	
	public StudentDirectory() throws IOException {
		db = DataFile.open(DIR_BASE_NAME);
	}
	private Map<String, Student> students =
			new HashMap<String, Student>();
	
	public void add(Student student) throws IOException {
		db.add(student.getId(), student);			
	}

	public Student findById(String id) throws IOException {
		// TODO Auto-generated method stub
		return (Student)db.findBy(id);
	}
	
	public void close() throws IOException {
		db.close();
	}
	public void remove() {
		db.deleteFiles();
	}
}
