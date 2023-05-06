package sis.studentinfo;

import junit.framework.*;

public class PerformanceTest extends TestCase{
	private static final double tolerance = 0.005;
	public void testAverage() {
		Performance performance = new Performance();
		performance.setNumberOfTests(4);
		performance.set(0, 98);
		performance.set(1,  92);
		performance.set(2, 81);
		performance.set(3, 72);
		
		assertEquals(92, performance.get(1));
		assertEquals(85.75, performance.average(), tolerance);
	}
	public void testInitialization() {
		Performance performance = new Performance();
		performance.setScores(1, 1, 1, 1);
		assertEquals(1.0, performance.average(), tolerance);
		performance.setScores(0, 0, 1, 1);
		assertEquals(0.5, performance.average(), tolerance);
		performance.setScores(75, 72, 90, 60);
		assertEquals(74.25, performance.average(), tolerance);
	}
	public void testAverageForNoScores() {
		Performance performance = new Performance();
		//assertEquals(0.0, performance.average());
		assertTrue(Double.isNaN(performance.average()));
	}
	public void testFloatInfinity() {
		final float tolerance = 0.5f;
		final float x = 1f;
		
		assertEquals(
				Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY * 100, tolerance);
		assertEquals(Float.NEGATIVE_INFINITY,
				Float.POSITIVE_INFINITY * -1, tolerance);
		
		assertEquals(Float.POSITIVE_INFINITY, x / 0f, tolerance);
		assertEquals(Float.NEGATIVE_INFINITY, x / -0f, tolerance);
		assertTrue(Float.isNaN(x % 0f));
		
		assertEquals(0f, x / Float.POSITIVE_INFINITY, tolerance);
		assertEquals(-0f, x/ Float.NEGATIVE_INFINITY, tolerance);
		assertEquals(x, x % Float.POSITIVE_INFINITY, tolerance);
		
		assertTrue(Float.isNaN(0f / 0f));
		assertTrue(Float.isNaN(0f % 0f));
		
		assertEquals(
				Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY / x, tolerance);
		assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY / x, tolerance);
		assertTrue(Float.isNaN(Float.POSITIVE_INFINITY % 1f));
		
		assertTrue(
				Float.isNaN(Float.POSITIVE_INFINITY / Float.POSITIVE_INFINITY));
		assertTrue(
				Float.isNaN(Float.POSITIVE_INFINITY % Float.POSITIVE_INFINITY));
		assertTrue(
				Float.isNaN(Float.POSITIVE_INFINITY / Float.NEGATIVE_INFINITY));
		assertTrue(
				Float.isNaN(Float.POSITIVE_INFINITY % Float.NEGATIVE_INFINITY));
		assertTrue(
				Float.isNaN(Float.NEGATIVE_INFINITY / Float.POSITIVE_INFINITY));
		assertTrue(
				Float.isNaN(Float.NEGATIVE_INFINITY % Float.POSITIVE_INFINITY));
		assertTrue(
				Float.isNaN(Float.NEGATIVE_INFINITY / Float.NEGATIVE_INFINITY));
		assertTrue(
				Float.isNaN(Float.NEGATIVE_INFINITY % Float.NEGATIVE_INFINITY));
	
		byte b = Byte.MAX_VALUE;
		assertEquals(Byte.MAX_VALUE + 1, b + 1);
		
		b = Byte.MAX_VALUE;
		assertEquals(Byte.MAX_VALUE + 1, b + 1);
		b += 1;
		assertEquals(Byte.MIN_VALUE, b);
		
		assertTrue(Double.isInfinite(Double.MAX_VALUE * Double.MAX_VALUE));
	}
	public void testBoolean() {
		assertEquals(0, 0 & 0);
		assertEquals(0, 0 & 1);
		assertEquals(0, 1 & 0);
		assertEquals(1, 1 & 1);
		
		assertEquals(0, 0 | 0);
		assertEquals(1, 0 | 1);
		assertEquals(1, 1 | 0);
		assertEquals(1, 1 | 1);
		
		assertEquals(0, 0 ^ 0);
		assertEquals(1, 0 ^ 1);
		assertEquals(1, 1 ^ 0);
		assertEquals(0, 1 ^ 1);
		
		int x = 0x7FFFFFF1;
		assertEquals(0x8000000E, ~x);
		
	}
}
