package sis.report;
import junit.framework.*;
import java.util.*;
import sis.studentinfo.*;
import static sis.report.ReportConstant.NEWLINE;
public class CourseReportTest extends TestCase{
	public void testReport() {
		final Date date = new Date();
		CourseReport report = new CourseReport();
		report.add(create("ENGL","101", date));
		report.add(create("ITAL", "410", date));
		report.add(create("CZEC", "200", date));
		report.add(create("CZEC", "220", date));
		report.add(create("ITAL", "330", date));
		
		assertEquals(
				"CZEC 200" + NEWLINE +
				"CZEC 220" + NEWLINE +
				"ENGL 101" + NEWLINE +
				"ITAL 330" + NEWLINE +
				"ITAL 410" + NEWLINE ,
			report.text());
	}
	private Session create(String name, String number, Date date) {
		// TODO Auto-generated method stub
		return CourseSession.create(new Course(name, number), date);
	}
	public void testStringCompareTo() {
		assertTrue("A".compareTo("B") < 0);
		assertEquals(0, "A".compareTo("A"));
		assertTrue("B".compareTo("A") > 0);
	System.out.println("vlaue= " + (3 * 0.3));
	}
}
