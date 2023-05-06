package sis.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import sis.studentinfo.*;

public class RosterPorter {
	static final String NEWLINE =
			System.getProperty("line.separator");
	static final String ROSTER_REPORT_HEADER =
			"Student %n-%n";
	static final String ROSTER_REPORT_FOOTER =
			"%n# STUDENTS = %d%n";
	Session session;
	private Writer writer;
	
	public RosterPorter(Session session) {
		this.session = session;
	}
	String getReport() {
		StringBuilder buffer = new StringBuilder();
		
		return writer.toString();
	}
	private void writeHeader() throws IOException {
		writer.write(String.format(ROSTER_REPORT_HEADER));
		//		buffer.append(ROSTER_REPORT_HEADER);
	}
	private void writeFooter() throws IOException{
		writer.write(String.format(ROSTER_REPORT_FOOTER, session.getAllStudents().size()));
	}
	private void writeBody() throws IOException {
		for (Student student : session.getAllStudents()) {
			writer.write(String.format(student.getName() + "%n"));
		}
	}
	public void writeReport(Writer writer) throws IOException {
		// TODO Auto-generated method stub
		this.writer =  writer;
		writeHeader();
		writeBody();
		writeFooter();		
	}
	public void writeReport(String filename) throws IOException {
		Writer bufferedWriter =
				new BufferedWriter(new FileWriter(filename));
		try {
			writeReport(bufferedWriter);
		}
		finally {
			bufferedWriter.close();
		}
		
	}
}
