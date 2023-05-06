package sis.ui;
import sis.studentinfo.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class StudentUI {

	static final String MENU = "(A)dd or (Q)uit?";
	public static final String ADD_OPTION = "A";
	public static final String NAME_PROMPT = "Name:";
	private BufferedReader reader;
	private BufferedWriter writer;
	private List<Student> students = new ArrayList<Student>();
	static String QUIT_OPTION = "Q";
	static String ADDED_MESSAGE = "Added";

	public StudentUI() {
		this.reader = 
				new BufferedReader(new InputStreamReader(System.in));
		this.writer = 
				new BufferedWriter(new OutputStreamWriter(System.out));
	}

	public void run() throws IOException {
		String line;
		do {
			write(MENU);
			line = reader.readLine();
			if (line.equals(ADD_OPTION))
				addStudent();
		} while(!line.equals(QUIT_OPTION));
	}
	private void addStudent() throws IOException {
		write(NAME_PROMPT);
		String name = reader.readLine();	
		
		students.add(new Student(name));
		writeln(ADDED_MESSAGE);
	}

	private void writeln(String line) throws IOException {
		writer.write(line, 0, line.length());
		writer.newLine();
		writer.flush();
	}

	private void write(String line) throws IOException {
		writer.write(line, 0, line.length());
		writer.flush();		
	}

	public List<Student> getAddedStudents() {
		// TODO Auto-generated method stub
		return students;
	}
	public static final void main(String[] args) throws IOException {
		new StudentUI().run();
	}
}
