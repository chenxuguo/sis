package sis.report;
import junit.framework.TestCase;
import sis.studentinfo.CourseSession;
import sis.studentinfo.DateUtil;
import sis.studentinfo.*;
import static sis.report.ReportConstant.NEWLINE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

public class RosterReporterTest extends TestCase{
	private Session session;
	
	protected void setUp() {
		session =
				CourseSession.create(
						new Course("ENGL", "101"), 
						DateUtil.createDate(2003, 1, 6));
		session.enroll(new Student("A"));
		session.enroll(new Student("B"));				
	}
	public void testRosterReport() throws IOException {		
		Writer writer = new StringWriter();
		new RosterPorter(session).writeReport(writer);
		assertReportContents(writer.toString());
	}
	private void assertReportContents(String rosterReport) {
		assertEquals(
				String.format(RosterPorter.ROSTER_REPORT_HEADER + 
					"A%n" +  
					"B%n" + 
					RosterPorter.ROSTER_REPORT_FOOTER, session.getNumberOfStudents()), 
				rosterReport);
	}
	public void testSortStringInPlace() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Heller");
		list.add("Kafka");
		list.add("Camus");
		list.add("Boyle");
		java.util.Collections.sort(list);
		assertEquals("Boyle", list.get(0));
		assertEquals("Camus", list.get(1));
		assertEquals("Heller", list.get(2));
		assertEquals("Kafka", list.get(3));
	}
	public void testSortStringInNewList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Heller");
		list.add("Kafka");
		list.add("Camua");
		list.add("Boyle");
		ArrayList<String> sortedList = new ArrayList<String>(list);
		
		assertEquals("Heller", list.get(0));
		assertEquals("Kafka", list.get(1));
		assertEquals("Camua", list.get(2));
		assertEquals("Boyle", list.get(3));
	}
	public void testFileReport() throws IOException {
		final String filename = "testFileReport.txt";
		try {
			delete(filename);
			new RosterPorter(session).writeReport(filename);
			
			StringBuffer buffer = new StringBuffer();
			String line;
			
			BufferedReader reader =
				new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null)
				buffer.append(String.format(line + "%n"));
			reader.close();
			
			assertReportContents(buffer.toString());
		}
		finally {
			delete(filename);
		}
	}
	private void delete(String filename) {
		File file = new File(filename);
		if (file.exists())
			assertTrue("unable to delete " + filename, file.delete());
		
	}
}
