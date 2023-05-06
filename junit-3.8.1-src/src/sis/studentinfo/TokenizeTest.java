package sis.studentinfo;

import junit.framework.*;
import java.util.*;

public class TokenizeTest extends TestCase {
	public void testTokenize() {
		List<String> nameParts = Student.tokenize("Jane Doe Jr.");
		assertEquals("Jane", nameParts.get(0));
		assertEquals("Doe", nameParts.get(1));
		assertEquals("Jr.", nameParts.get(2));
	}
}
