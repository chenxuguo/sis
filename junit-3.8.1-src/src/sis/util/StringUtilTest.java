package sis.util;

import junit.framework.*;

public class StringUtilTest extends TestCase{
	private static final String TEXT = "this is it, isn't it";
	public void testOccurenceOne() {
		assertEquals(1, StringUtil.occurrences(TEXT, "his"));
		
	}
	public  void testOccurrenceNone() {
		assertEquals(0, StringUtil.occurrences(TEXT, "smelt"));
		
	}
	public void testOccurrencemany() {
		assertEquals(3, StringUtil.occurrences(TEXT, "is"));
		assertEquals(2, StringUtil.occurrences(TEXT, "it"));
		
		
	}
	public void testOccurrencesSEarchStringTooLarge() {
		assertEquals(0, StringUtil.occurrences(TEXT, TEXT + "sdfas"));
	}
	
}
