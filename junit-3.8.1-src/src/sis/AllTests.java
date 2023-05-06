package sis;
import junit.framework.*;

public class AllTests {
	public static TestSuite suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(sis.report.AllTests.suite());
		suite.addTest(sis.studentinfo.AllTests.suite());
			
		return suite;
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
