package sis.studentinfo;

import java.util.*;

import junit.framework.*;

public class CourseTest extends TestCase {
	public void testCreate() {
		Course course = new Course("CMSC", "120");
		assertEquals("CMSC", course.getDepartment());
		assertEquals("120", course.getNumber());
	}
	public void testEquality() {
		Course courseA = new Course("NUR3", "201");
		Course courseAPrime = new Course("NUR3", "201");
		assertEquals(courseA, courseAPrime);
		
		assertEquals(courseA, courseA);
		
		Course courseAPrime2 = new Course("NUR3", "201");
		assertEquals(courseAPrime, courseAPrime2);
		assertEquals(courseA, courseAPrime2);
		
		assertEquals(courseAPrime, courseA);
		
		assertEquals(courseA, courseAPrime);
		
		assertFalse(courseA.equals(null));
		
		assertFalse(courseA.equals("CMSC-120"));
		
		List<Course> list = new ArrayList<Course>();
		list.add(courseA);
		assertTrue(list.contains(courseAPrime));
		
		courseA = new Course("NURS", "201");
		courseAPrime = new Course("NURS", "201");
		Map<Course, String> map = new HashMap<Course, String>();
		map.put(courseA, "");
		assertTrue(map.containsKey(courseAPrime));
	}
	public void testhashCodePerformance() {
		final int count = 10000;
		long start = System.currentTimeMillis();
		Map<Course, String> map = new HashMap<Course, String>();
		for (int i = 0; i < count; i++) {
			Course course = new Course("C" + i, "" + i);
			map.put(course,  "");
		}
		long stop = System.currentTimeMillis();
		long elapsed = stop - start;
		final long arbitraryThreshold = 100;
		assertTrue("elapsed time = " + elapsed,
				elapsed < arbitraryThreshold);
	}
}
